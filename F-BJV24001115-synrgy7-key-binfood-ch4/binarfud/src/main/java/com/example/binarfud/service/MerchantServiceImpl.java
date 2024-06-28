package com.example.binarfud.service;

import com.example.binarfud.model.Merchant;
import com.example.binarfud.repository.MerchantRepository;
import com.example.binarfud.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.UUID;

@Service
public class MerchantServiceImpl implements MerchantService {

    private static final Logger logger = LoggerFactory.getLogger(MerchantServiceImpl.class);

    @Autowired
    private MerchantRepository merchantRepository;

    @Override
    public List<Merchant> findAll() {
        logger.info("Fetching all merchants");
        return merchantRepository.findAll();
    }

    @Override
    public Merchant findById(UUID id) {
        logger.debug("Fetching merchant with id: {}", id);
        return merchantRepository.findById(id).orElse(null);
    }

    @Override
    public Merchant save(Merchant merchant) {
        logger.info("Saving merchant: {}", merchant.getMerchantName());
        return merchantRepository.save(merchant);
    }

    @Override
    public void deleteById(UUID id) {
        logger.warn("Deleting merchant with id: {}", id);
        merchantRepository.deleteById(id);
    }

    @Override
    public Page<Merchant> findPaginated(int page, int size) {
        logger.debug("Fetching merchants with pagination - page: {}, size: {}", page, size);
        return merchantRepository.findAll(PageRequest.of(page, size));
    }
}
