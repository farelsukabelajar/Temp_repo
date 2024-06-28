package com.example.binarfud.service;

import com.example.binarfud.model.User;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface UserService {
    List<User> findAll();
    User findById(UUID id);
    User save(User user);
    void deleteById(UUID id);
    Page<User> findPaginated(int page, int size);
}
