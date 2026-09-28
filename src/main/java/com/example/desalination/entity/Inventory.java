package com.example.desalination.entity;

import jakarta.persistence.*;

/**
 * Tracks the current stock quantity of one item type.
 * We keep exactly one row per itemName (e.g. "DESALINATED_WATER", "INDUSTRIAL_SALT", "RO_MEMBRANE")
 * and simply add/subtract quantity as things happen - no batch/lot tracking, kept intentionally simple.
 */
@Entity
@Table(name = "inventory")
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String itemName;
    private String unit;
    private Double quantity = 0.0;

    public Inventory() {
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getItemName() { return itemName; }
    public void setItemName(String itemName) { this.itemName = itemName; }

    public String getUnit() { return unit; }
    public void setUnit(String unit) { this.unit = unit; }

    public Double getQuantity() { return quantity; }
    public void setQuantity(Double quantity) { this.quantity = quantity; }
}
