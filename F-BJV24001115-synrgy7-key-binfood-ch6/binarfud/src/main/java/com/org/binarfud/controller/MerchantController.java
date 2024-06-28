package com.org.binarfud.controller;

import com.org.binarfud.dto.MerchantDTO;
import com.org.binarfud.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/merchants")
public class MerchantController {

    @Autowired
    private MerchantService merchantService;

    @GetMapping
    public ResponseEntity<List<MerchantDTO>> getAllMerchants() {
        return ResponseEntity.ok(merchantService.getAllMerchants());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MerchantDTO> getMerchantById(@PathVariable UUID id) {
        MerchantDTO merchantDTO = merchantService.getMerchantById(id);
        return merchantDTO != null ? ResponseEntity.ok(merchantDTO) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<MerchantDTO> createMerchant(@RequestBody MerchantDTO merchantDTO) {
        return ResponseEntity.ok(merchantService.saveMerchant(merchantDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMerchant(@PathVariable UUID id) {
        merchantService.deleteMerchant(id);
        return ResponseEntity.noContent().build();
    }
}
