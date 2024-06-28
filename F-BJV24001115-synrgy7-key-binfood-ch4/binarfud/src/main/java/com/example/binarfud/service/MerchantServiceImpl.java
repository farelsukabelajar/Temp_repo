package com.example.binarfud.service;

import com.example.binarfud.dto.MerchantDTO;
import com.example.binarfud.model.Merchant;
import com.example.binarfud.repository.MerchantRepository;
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
public class MerchantServiceImpl implements MerchantService {

    private static final Logger logger = LoggerFactory.getLogger(MerchantServiceImpl.class);

    @Autowired
    private MerchantRepository merchantRepository;

    @Override
    public List<MerchantDTO> findAll() {
        logger.info("Fetching all merchants");
        return merchantRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public MerchantDTO findById(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("Merchant ID cannot be null");
        }

        logger.debug("Fetching merchant with id: {}", id);
        return merchantRepository.findById(id).map(this::convertToDTO).orElse(null);
    }

    @Override
    public MerchantDTO save(MerchantDTO merchantDTO) {
        if (merchantDTO == null) {
            throw new IllegalArgumentException("MerchantDTO cannot be null");
        }

        logger.info("Saving merchant: {}", merchantDTO.getMerchantName());
        Merchant merchant = merchantRepository.save(convertToEntity(merchantDTO));
        return convertToDTO(merchant);
    }

    @Override
    public void deleteById(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("Merchant ID cannot be null");
        }

        logger.warn("Deleting merchant with id: {}", id);
        merchantRepository.deleteById(id);
    }

    @Override
    public Page<MerchantDTO> findPaginated(int page, int size) {
        logger.debug("Fetching merchants with pagination - page: {}, size: {}", page, size);
        Page<Merchant> merchantPage = merchantRepository.findAll(PageRequest.of(page, size));
        List<MerchantDTO> merchantDTOs = merchantPage.getContent().stream().map(this::convertToDTO).collect(Collectors.toList());
        return new PageImpl<>(merchantDTOs, PageRequest.of(page, size), merchantPage.getTotalElements());
    }

    private MerchantDTO convertToDTO(Merchant merchant) {
        if (merchant == null) {
            return null;
        }

        MerchantDTO merchantDTO = new MerchantDTO();
        merchantDTO.setMerchantId(merchant.getMerchantId());
        merchantDTO.setMerchantName(merchant.getMerchantName());
        merchantDTO.setMerchantLocation(merchant.getMerchantLocation());
        merchantDTO.setOpen(merchant.isOpen());
        return merchantDTO;
    }

    private Merchant convertToEntity(MerchantDTO merchantDTO) {
        if (merchantDTO == null) {
            return null;
        }

        Merchant merchant = new Merchant();
        merchant.setMerchantId(merchantDTO.getMerchantId());
        merchant.setMerchantName(merchantDTO.getMerchantName());
        merchant.setMerchantLocation(merchantDTO.getMerchantLocation());
        merchant.setOpen(merchantDTO.isOpen());
        return merchant;
    }
}
