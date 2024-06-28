package com.example.binarfud.controller;

import com.example.binarfud.model.Merchant;
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
    public ResponseEntity<Merchant> addMerchant(@RequestBody Merchant merchant) {
        Merchant savedMerchant = merchantService.save(merchant);
        return new ResponseEntity<>(savedMerchant, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Merchant> updateMerchant(@PathVariable UUID id, @RequestBody Merchant merchant) {
        merchant.setMerchantId(id);
        Merchant updatedMerchant = merchantService.save(merchant);
        return new ResponseEntity<>(updatedMerchant, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Merchant>> getAllMerchants() {
        List<Merchant> merchants = merchantService.findAll();
        return new ResponseEntity<>(merchants, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Merchant> getMerchantById(@PathVariable UUID id) {
        Merchant merchant = merchantService.findById(id);
        return new ResponseEntity<>(merchant, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMerchant(@PathVariable UUID id) {
        merchantService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
