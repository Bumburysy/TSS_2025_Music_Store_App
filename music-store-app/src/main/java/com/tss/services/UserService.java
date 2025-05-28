package com.tss.services;

import com.tss.dto.UserDTO;
import com.tss.entities.User;
import com.tss.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public UserDTO getUserById(String id) {
        return userRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new IllegalArgumentException("Nieprawidłowe ID: " + id));
    }

    public UserDTO saveUser(User user) {
        return convertToDTO(userRepository.save(user));
    }
    
    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }

    private UserDTO convertToDTO(User user) {
        return new UserDTO(user.getId(), user.getFirstname(), user.getLastname(), user.getEmail(), user.getRole());
    }
    
}




