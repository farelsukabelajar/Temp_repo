package com.example.binarfud.service;

import com.example.binarfud.dto.MerchantDTO;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface MerchantService {
    List<MerchantDTO> findAll();
    MerchantDTO findById(UUID id);
    MerchantDTO save(MerchantDTO merchantDTO);
    void deleteById(UUID id);
    Page<MerchantDTO> findPaginated(int page, int size);
}
