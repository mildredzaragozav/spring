package com.belle.springsecurityjwt.service;

import com.belle.springsecurityjwt.model.AuthenticationRequest;
import com.belle.springsecurityjwt.model.AppUser;
import com.belle.springsecurityjwt.repository.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AppUserService {
    private final PasswordEncoder encoder;
    private final AppUserRepository appUserRepository;

    public ResponseEntity<String> registerNewUser(AuthenticationRequest userRequest) {
        AppUser user = AppUser.builder()
                .username(userRequest.username())
                .password(encoder.encode(userRequest.password()))
                .build();

        appUserRepository.save(user);

        return ResponseEntity.ok("User registered successfully.");
    }
}
