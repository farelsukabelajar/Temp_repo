package com.example.binarfud.service;

import com.example.binarfud.dto.UserDTO;
import com.example.binarfud.model.User;
import com.example.binarfud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserDTO> findAll() {
        logger.info("Fetching all users");
        return userRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public UserDTO findById(UUID id) {
        logger.debug("Fetching user with id: {}", id);
        return userRepository.findById(id).map(this::convertToDTO).orElse(null);
    }

    @Override
    public UserDTO save(UserDTO userDTO) {
        logger.info("Saving user: {}", userDTO.getUsername());
        User user = userRepository.save(convertToEntity(userDTO));
        return convertToDTO(user);
    }

    @Override
    public void deleteById(UUID id) {
        logger.warn("Deleting user with id: {}", id);
        userRepository.deleteById(id);
    }

    @Override
    public Page<UserDTO> findPaginated(int page, int size) {
        logger.debug("Fetching users with pagination - page: {}, size: {}", page, size);
        Page<User> userPage = userRepository.findAll(PageRequest.of(page, size));
        List<UserDTO> userDTOs = userPage.getContent().stream().map(this::convertToDTO).collect(Collectors.toList());
        return new PageImpl<>(userDTOs, PageRequest.of(page, size), userPage.getTotalElements());
    }

    private UserDTO convertToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(user.getUserId());
        userDTO.setUsername(user.getUsername());
        userDTO.setEmailAddress(user.getEmailAddress());
        return userDTO;
    }

    private User convertToEntity(UserDTO userDTO) {
        User user = new User();
        user.setUserId(userDTO.getUserId());
        user.setUsername(userDTO.getUsername());
        user.setEmailAddress(userDTO.getEmailAddress());
        // password bisa diatur di tempat lain jika diperlukan
        return user;
    }
}
