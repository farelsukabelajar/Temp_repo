package com.example.binarfud.service;

import com.example.binarfud.model.User;
import com.example.binarfud.repository.UserRepository;
import com.example.binarfud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAll() {
        logger.info("Fetching all users");
        return userRepository.findAll();
    }

    @Override
    public User findById(UUID id) {
        logger.debug("Fetching user with id: {}", id);
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User save(User user) {
        logger.info("Saving user: {}", user.getUsername());
        return userRepository.save(user);
    }

    @Override
    public void deleteById(UUID id) {
        logger.warn("Deleting user with id: {}", id);
        userRepository.deleteById(id);
    }

    @Override
    public Page<User> findPaginated(int page, int size) {
        logger.debug("Fetching users with pagination - page: {}, size: {}", page, size);
        return userRepository.findAll(PageRequest.of(page, size));
    }
}
