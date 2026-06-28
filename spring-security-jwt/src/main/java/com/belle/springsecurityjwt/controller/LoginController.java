package com.belle.springsecurityjwt.controller;

import com.belle.springsecurityjwt.model.AuthenticationRequest;
import com.belle.springsecurityjwt.service.CustomUserDetailsService;
import com.belle.springsecurityjwt.service.CustomUserService;
import com.belle.springsecurityjwt.util.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/login")
@AllArgsConstructor
public class LoginController {
    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService customUserDetailsService;
    private final CustomUserService customUserService;

    @PostMapping
    public ResponseEntity<String> loginUser(@RequestBody AuthenticationRequest user) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                user.username(),
                user.password()
        );

        authenticationManager.authenticate(token);
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(user.username());
        return ResponseEntity.ok(JwtUtil.generateToken(userDetails));
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody AuthenticationRequest user) {
        return customUserService.registerNewUser(user);
    }
}
