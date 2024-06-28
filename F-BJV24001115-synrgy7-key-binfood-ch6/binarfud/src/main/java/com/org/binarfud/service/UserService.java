package com.org.binarfud.service;

import com.org.binarfud.dto.UserDTO;
import com.org.binarfud.model.User;
import com.org.binarfud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public UserDTO getUserById(UUID id) {
        return userRepository.findById(id).map(this::convertToDTO).orElse(null);
    }

    public UserDTO saveUser(UserDTO userDTO) {
        User user = convertToEntity(userDTO);
        user = userRepository.save(user);
        return convertToDTO(user);
    }

    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }

    private UserDTO convertToDTO(User user) {
        return UserDTO.builder()
                .usersId(user.getUsersId())
                .username(user.getUsername())
                .emailAddress(user.getEmailAddress())
                .password(user.getPassword())
                .build();
    }

    private User convertToEntity(UserDTO userDTO) {
        return User.builder()
                .usersId(userDTO.getUsersId())
                .username(userDTO.getUsername())
                .emailAddress(userDTO.getEmailAddress())
                .password(userDTO.getPassword())
                .build();
    }
}
