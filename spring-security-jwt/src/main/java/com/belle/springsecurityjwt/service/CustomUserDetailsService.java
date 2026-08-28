package com.belle.springsecurityjwt.service;

import com.belle.springsecurityjwt.model.AppUser;
import com.belle.springsecurityjwt.repository.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

        private final AppUserRepository appUserRepository;

        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            AppUser customUser = appUserRepository.findByUsername(username);

            if (customUser == null) {
                throw new UsernameNotFoundException("User '" + username + "' not found.");
            }

            return User
                    .withUsername(customUser.getUsername())
                    .password(customUser.getPassword())
                    .build();
        }
}
