package com.belle.springsecurityjwt.service;

import com.belle.springsecurityjwt.model.AppUser;
import com.belle.springsecurityjwt.repository.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;


@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

        private final AppUserRepository appUserRepository;

        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            AppUser appUser = appUserRepository
                    .findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User '" + username + "' not found."));

            return User
                    .withUsername(appUser.getUsername())
                    .password(appUser.getPassword())
                    .authorities(getAuthorities(appUser))
                    .build();
        }

        private Collection<? extends GrantedAuthority> getAuthorities(AppUser appUser) {
            return List.of(new SimpleGrantedAuthority(appUser.getAuthority()));
        }
}
