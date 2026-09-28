package com.example.desalination.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.math.BigDecimal;

/**
 * Master data: anything the plant sells or buys.
 * productType is a plain String to keep things simple, e.g.
 *   "WATER"     -> Potable Desalinated Water
 *   "MINERAL"   -> Industrial Grade Salt Byproduct
 *   "MEMBRANE"  -> RO Membrane Replacement (purchased, not sold)
 */
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Product name is required")
    private String productName;

    @NotBlank(message = "Product type is required")
    private String productType;

    private String unit;     // e.g. m3, kg, piece
    private BigDecimal price;

    public Product() {
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }

    public String getProductType() { return productType; }
    public void setProductType(String productType) { this.productType = productType; }

    public String getUnit() { return unit; }
    public void setUnit(String unit) { this.unit = unit; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }
}
