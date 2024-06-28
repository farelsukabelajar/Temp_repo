package com.example.binarfud.service;

import com.example.binarfud.model.Merchant;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface MerchantService {
    List<Merchant> findAll();
    Merchant findById(UUID id);
    Merchant save(Merchant merchant);
    void deleteById(UUID id);
    Page<Merchant> findPaginated(int page, int size);
}
