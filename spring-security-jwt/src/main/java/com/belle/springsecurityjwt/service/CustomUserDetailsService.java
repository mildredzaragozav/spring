package com.belle.springsecurityjwt.service;

import com.belle.springsecurityjwt.model.CustomUser;
import com.belle.springsecurityjwt.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

        private final UserRepository userRepository;

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
}
