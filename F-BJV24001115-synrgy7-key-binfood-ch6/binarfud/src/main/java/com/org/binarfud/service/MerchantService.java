package com.org.binarfud.service;

import com.org.binarfud.dto.MerchantDTO;
import com.org.binarfud.model.Merchant;
import com.org.binarfud.repository.MerchantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MerchantService {

    @Autowired
    private MerchantRepository merchantRepository;

    public List<MerchantDTO> getAllMerchants() {
        return merchantRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public MerchantDTO getMerchantById(UUID id) {
        return merchantRepository.findById(id).map(this::convertToDTO).orElse(null);
    }

    public MerchantDTO saveMerchant(MerchantDTO merchantDTO) {
        Merchant merchant = convertToEntity(merchantDTO);
        merchant = merchantRepository.save(merchant);
        return convertToDTO(merchant);
    }

    public void deleteMerchant(UUID id) {
        merchantRepository.deleteById(id);
    }

    private MerchantDTO convertToDTO(Merchant merchant) {
        return MerchantDTO.builder()
                .id(merchant.getId())
                .name(merchant.getName())
                .location(merchant.getLocation())
                .isOpen(merchant.isOpen())
                .build();
    }

    private Merchant convertToEntity(MerchantDTO merchantDTO) {
        return Merchant.builder()
                .id(merchantDTO.getId())
                .name(merchantDTO.getName())
                .location(merchantDTO.getLocation())
                .isOpen(merchantDTO.isOpen())
                .build();
    }
}
