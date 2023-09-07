package com.spence.aaron.ChatAppBackend.services;

import com.spence.aaron.ChatAppBackend.entities.UserEntity;
import com.spence.aaron.ChatAppBackend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        // Building a UserDetails object from the returned user entity
        return User.builder()
                .username(userEntity.getUsername())
                .password(userEntity.getPassword())
                .authorities("ROLE_USER")  // default role for all users
                .build();
    }
}
