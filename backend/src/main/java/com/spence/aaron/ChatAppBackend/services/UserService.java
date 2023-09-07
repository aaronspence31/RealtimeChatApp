package com.spence.aaron.ChatAppBackend.services;

import com.spence.aaron.ChatAppBackend.entities.UserEntity;
import com.spence.aaron.ChatAppBackend.exceptions.UserAlreadyExistsException;
import com.spence.aaron.ChatAppBackend.exceptions.UserNotFoundException;
import com.spence.aaron.ChatAppBackend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Register and create a new user
    public UserEntity registerUser(UserEntity userEntity) {
        validateUser(userEntity);
        Optional<UserEntity> existingUser = userRepository.findByUsername(userEntity.getUsername());
        if (existingUser.isPresent()) {
            throw new UserAlreadyExistsException("User already exists with username: " + userEntity.getUsername());
        }
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        return userRepository.save(userEntity);
    }

    // Get all users
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    // Get a user by ID
    public Optional<UserEntity> getUserById(Long id) {
        return userRepository.findById(id);
    }

    // Update a user
    public UserEntity updateUser(UserEntity userEntity) {
        validateUser(userEntity);

        Optional<UserEntity> existingUser = userRepository.findById(userEntity.getId());
        if (!existingUser.isPresent()) {
            throw new UserNotFoundException("User not found with ID: " + userEntity.getId());
        }

        // Override the password from the request with the existing password
        userEntity.setPassword(existingUser.get().getPassword());

        return userRepository.save(userEntity);
    }

    // Delete a user
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("User not found with ID: " + id);
        }
        userRepository.deleteById(id);
    }

    // Validate user attributes
    private void validateUser(UserEntity userEntity) {
        if (StringUtils.isEmpty(userEntity.getUsername())) {
            throw new IllegalArgumentException("Username cannot be empty");
        }

        if (userEntity.getPassword() == null || userEntity.getPassword().trim().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty");
        }

        if (userEntity.getPassword().length() < 8) {
            throw new IllegalArgumentException("Password must be at least 8 characters long");
        }
        // Add more validation as needed, e.g., email format, etc.
    }
}
