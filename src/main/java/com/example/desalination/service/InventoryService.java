package com.example.desalination.service;

import com.example.desalination.entity.Inventory;
import com.example.desalination.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Manages stock levels for Desalinated Water, Industrial Salt and RO Membranes.
 * We keep exactly one Inventory row per item name and just add/subtract quantity -
 * simple running-total logic, no batch tracking.
 */
@Service
public class InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    public List<Inventory> findAll() {
        return inventoryRepository.findAll();
    }

    /** Finds the inventory row for an item, creating it (starting at 0) if it does not exist yet. */
    public Inventory getOrCreate(String itemName, String unit) {
        Optional<Inventory> existing = inventoryRepository.findAll().stream()
                .filter(i -> i.getItemName().equalsIgnoreCase(itemName))
                .findFirst();
        if (existing.isPresent()) {
            return existing.get();
        }
        Inventory inventory = new Inventory();
        inventory.setItemName(itemName);
        inventory.setUnit(unit);
        inventory.setQuantity(0.0);
        return inventoryRepository.save(inventory);
    }

    public void addQuantity(String itemName, String unit, double amount) {
        Inventory inventory = getOrCreate(itemName, unit);
        inventory.setQuantity(inventory.getQuantity() + amount);
        inventoryRepository.save(inventory);
    }

    public void subtractQuantity(String itemName, String unit, double amount) {
        Inventory inventory = getOrCreate(itemName, unit);
        inventory.setQuantity(inventory.getQuantity() - amount);
        inventoryRepository.save(inventory);
    }
}
