package com.ceres.blog.security.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    @PostMapping("/register")
    ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegistrationRequest registrationRequest
            ) throws Exception{
        AuthenticationResponse user = authenticationService.record(registrationRequest);
        return ResponseEntity.ok(user);
    }
    @PostMapping("/login")
    ResponseEntity<AuthenticationResponse> signIn(@RequestBody AuthenticationRequest request) throws Exception {
        return ResponseEntity.ok(authenticationService.authentication(request));
    }
}
