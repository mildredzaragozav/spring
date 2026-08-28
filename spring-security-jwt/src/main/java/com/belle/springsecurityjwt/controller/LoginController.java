package com.belle.springsecurityjwt.controller;

import com.belle.springsecurityjwt.model.AuthenticationRequest;
import com.belle.springsecurityjwt.service.AppUserService;
import com.belle.springsecurityjwt.util.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/login")
@AllArgsConstructor
public class LoginController {
    private final AppUserService appUserService;
    private final JwtUtil jwtUtil;


    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody AuthenticationRequest user) {
        return appUserService.registerNewUser(user);
    }

    @PostMapping("/token")
    public ResponseEntity<String> getJwtToken(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        return ResponseEntity.ok(jwtUtil.generateToken(userDetails));
        /* See 'Notes' for past implementation. */
    }
}
