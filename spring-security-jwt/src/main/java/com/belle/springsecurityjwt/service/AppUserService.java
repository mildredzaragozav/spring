package com.belle.springsecurityjwt.service;

import com.belle.springsecurityjwt.model.RegistrationRequest;
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

    public ResponseEntity<String> registerNewUser(RegistrationRequest registrationRequest) {
        AppUser user = AppUser.builder()
                .username(registrationRequest.username())
                .password(encoder.encode(registrationRequest.password()))
                .authority(registrationRequest.authority())
                .build();

        appUserRepository.save(user);

        return ResponseEntity.ok("User registered successfully.");
    }
}
