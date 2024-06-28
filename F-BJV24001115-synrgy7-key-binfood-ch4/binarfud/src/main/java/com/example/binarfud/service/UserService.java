package com.example.binarfud.service;

import com.example.binarfud.dto.UserDTO;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface UserService {
    List<UserDTO> findAll();
    UserDTO findById(UUID id);
    UserDTO save(UserDTO userDTO);
    void deleteById(UUID id);
    Page<UserDTO> findPaginated(int page, int size);
}
