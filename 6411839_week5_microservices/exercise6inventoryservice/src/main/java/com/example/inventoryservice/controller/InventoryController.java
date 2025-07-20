package com.example.inventoryservice.controller;
import com.example.inventoryservice.model.Inventory;
import com.example.inventoryservice.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/inventory")
public class InventoryController {
    @Autowired
    private InventoryRepository repo;

    @GetMapping("/product/{productId}")
    public ResponseEntity<Inventory> byProduct(@PathVariable Long productId) {
        Optional<Inventory> result = repo.findByProductId(productId);
        return result.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Inventory add(@RequestBody Inventory i) {
        return repo.save(i);
    }
}