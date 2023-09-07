package com.spence.aaron.ChatAppBackend.config;

import com.spence.aaron.ChatAppBackend.services.CustomUserDetailsService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public SecurityConfig(CustomUserDetailsService customUserDetailsService, PasswordEncoder passwordEncoder) {
        this.customUserDetailsService = customUserDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService)
                .passwordEncoder(passwordEncoder);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())  // disable CSRF protection
                .authorizeRequests(authRequests -> authRequests
                        .requestMatchers(org.springframework.http.HttpMethod.GET, "/api/public/**").permitAll() // Public GET endpoints
                        .requestMatchers(org.springframework.http.HttpMethod.POST, "/api/users/register").permitAll() // Registration endpoint for POST
                        .anyRequest().authenticated()  // All other endpoints require authentication
                )
                .formLogin(formLogin -> formLogin
                        .loginProcessingUrl("/api/login")  // Login endpoint
                        .defaultSuccessUrl("/api/login-success", true)  // Redirect to this URL on successful login
                )
                .logout(logout -> logout
                        .logoutUrl("/api/logout")  // Logout endpoint
                        .logoutSuccessUrl("/api/public/logout-success")  // Redirect to a public endpoint or page after logout
                        .invalidateHttpSession(true)  // Invalidate session
                        .deleteCookies("JSESSIONID")  // Delete session cookie
                );
        return http.build();
    }
}
