package com.example.desalination.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

/**
 * Master data: a company that BUYS the recovered brine minerals / industrial salt.
 */
@Entity
@Table(name = "mineral_buyer")
public class MineralBuyer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Buyer name is required")
    private String buyerName;

    private String contactPerson;
    private String phone;
    private String email;

    public MineralBuyer() {
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getBuyerName() { return buyerName; }
    public void setBuyerName(String buyerName) { this.buyerName = buyerName; }

    public String getContactPerson() { return contactPerson; }
    public void setContactPerson(String contactPerson) { this.contactPerson = contactPerson; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
