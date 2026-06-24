package com.belle.springsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable) //Allows for POST, PUT, DELETE mappings with authentication
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/open").permitAll()
                        .requestMatchers("/closed").authenticated()
                        .requestMatchers("/products").authenticated()
                        .requestMatchers(HttpMethod.POST, "/products").authenticated()
                        .requestMatchers("/basic").hasAnyAuthority("user", "admin")
                        .requestMatchers("/special").hasAuthority("admin"))
                .httpBasic(Customizer.withDefaults())
                .build();
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder) {
        UserDetails dev = User
                .withUsername("dev")
                .password(encoder.encode("dev"))
                .authorities("user")
                .build();

        UserDetails admin = User
                .withUsername("admin")
                .password(encoder.encode("admin"))
                .authorities("admin")
                .build();

        return new InMemoryUserDetailsManager(dev, admin);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
