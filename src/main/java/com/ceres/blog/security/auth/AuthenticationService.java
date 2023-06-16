package com.ceres.blog.security.auth;

import com.ceres.blog.exceptions.exception.UserExistsException;
import com.ceres.blog.security.config.JwtService;
import com.ceres.blog.users.Roles;
import com.ceres.blog.users.model.Users;
import com.ceres.blog.users.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UsersRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    /*
    * Method to handle registration of users
    * */
    public AuthenticationResponse record(RegistrationRequest request) throws Exception{
        /*
        * Build a user object
        * */
        var existing = repository.findByEmail(request.getEmail());
        if (existing != null){
            throw new UserExistsException("This user already exists.");
        }
        else {
            var user = Users.builder()
                    .name(request.getName())
                    .username(request.getUsername())
                    .roles(Roles.USER)
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .build();

            repository.save(user);

            /*
             * [1] Generate access and refresh tokens from details of the user built above
             * [2] User this information to build the authentication response object
             * */
            var accessToken = jwtService.generateToken(user);
            var refreshToken = jwtService.generateRefreshToken(user);
            return AuthenticationResponse.builder()
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
                    .build();
        }
    }
    /*
    * Method to handle authentication of users
    * */
    public AuthenticationResponse authentication(AuthenticationRequest request) throws Exception{
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var theUser = repository.findByEmail(request.getEmail());
        var accessToken = jwtService.generateToken(theUser);
        var refreshToken = jwtService.generateRefreshToken(theUser);
        /*
        * Build Authentication Response object for the user
        *
        * */
        return AuthenticationResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}
