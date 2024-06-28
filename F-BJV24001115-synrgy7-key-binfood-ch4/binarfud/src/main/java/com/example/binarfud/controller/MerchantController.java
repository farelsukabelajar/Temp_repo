package com.example.binarfud.controller;

import com.example.binarfud.dto.MerchantDTO;
import com.example.binarfud.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/merchants")
public class MerchantController {

    @Autowired
    private MerchantService merchantService;

    @PostMapping
    public ResponseEntity<MerchantDTO> addMerchant(@RequestBody MerchantDTO merchantDTO) {
        MerchantDTO savedMerchant = merchantService.save(merchantDTO);
        return new ResponseEntity<>(savedMerchant, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MerchantDTO> updateMerchant(@PathVariable UUID id, @RequestBody MerchantDTO merchantDTO) {
        merchantDTO.setMerchantId(id);
        MerchantDTO updatedMerchant = merchantService.save(merchantDTO);
        return new ResponseEntity<>(updatedMerchant, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<MerchantDTO>> getAllMerchants() {
        List<MerchantDTO> merchants = merchantService.findAll();
        return new ResponseEntity<>(merchants, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MerchantDTO> getMerchantById(@PathVariable UUID id) {
        MerchantDTO merchant = merchantService.findById(id);
        if (merchant == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(merchant, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMerchant(@PathVariable UUID id) {
        merchantService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
