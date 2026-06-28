package com.belle.springsecurityjwt.service;

import com.belle.springsecurityjwt.model.AuthenticationRequest;
import com.belle.springsecurityjwt.model.CustomUser;
import com.belle.springsecurityjwt.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

        private final UserRepository userRepository;
        private final PasswordEncoder encoder;

        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            CustomUser customUser = userRepository.findByUsername(username);

            if (customUser == null) {
                throw new UsernameNotFoundException("User '" + username + "' not found.");
            }

            return User
                    .withUsername(customUser.getUsername())
                    .password(customUser.getPassword())
                    .build();
        }

        public ResponseEntity<String> registerNewUser(AuthenticationRequest userRequest) {
            CustomUser user = CustomUser.builder()
                    .username(userRequest.username())
                    .password(encoder.encode(userRequest.password()))
                    .build();

            userRepository.save(user);

            return ResponseEntity.ok("User registered successfully.");
        }
}
