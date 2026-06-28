package com.belle.springsecurityjwt.service;

import com.belle.springsecurityjwt.model.AuthenticationRequest;
import com.belle.springsecurityjwt.model.CustomUser;
import com.belle.springsecurityjwt.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomUserService {
    private final PasswordEncoder encoder;
    private final UserRepository userRepository;

    public ResponseEntity<String> registerNewUser(AuthenticationRequest userRequest) {
        CustomUser user = CustomUser.builder()
                .username(userRequest.username())
                .password(encoder.encode(userRequest.password()))
                .build();

        userRepository.save(user);

        return ResponseEntity.ok("User registered successfully.");
    }
}
