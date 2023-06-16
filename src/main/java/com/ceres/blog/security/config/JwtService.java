package com.ceres.blog.security.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    @Value("${application.security.jwt.secret-key}")
    private String secretKey;
    @Value("${application.security.jwt.expiration}")
    private long jwtExpiration;
    @Value("${application.security.jwt.refresh-token.expiration}")
    private long refreshExpiration;
    /*
    * Use the above defined secret key to create a Signing key for signing the token
    * */
    private Key getSigningKey(){
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    /*
    * Extract all claims from the token
    * */
    private Claims extractAllClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    /**
     *
     *
     */

    public <T> T extractSingleClaim(String token, Function<Claims,T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    /*
    * Create method to extract username from token
    * */
    public String extractUsername(String token){
        return extractSingleClaim(token, Claims::getSubject);
    }
    /*
    * create method to build a signed token from userDetails and set its time
    * of issuing as well as its expiration time.
    * */
    public String buildToken(
            Map<String,Object> extraClaims,
            UserDetails userDetails,
            long expiration
    ){
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }
    /*
    * Method for generating refresh token
    * */
    public String generateRefreshToken(UserDetails userDetails){
        return buildToken(new HashMap<>(),userDetails,refreshExpiration);
    }
    /*
     * Method for generating jwt token dynamically
     * */
    public String generateToken(
            Map<String,Object> extraClaims,
            UserDetails userDetails
    ){
        return buildToken(extraClaims,userDetails,jwtExpiration );
    }
    /*
     * use the above declared method to generate jwt token
     * */
    public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>(),userDetails);
    }
    /*
     * Method to extract the expiration status of the token
     *
     * */
    private Date extractExpiration(String token) {
        return extractSingleClaim(token,Claims::getExpiration);
    }
    /*
     * Use the above defined method to check whether a given jwt token has expired or not
     * */
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
    /*
     * Method for checking validity of jwt token by checking its expiration status as well
     * as comparing username of the user presenting this token with the username the token bares
     * */
    public boolean isTokenValid(String token, UserDetails userDetails){
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
