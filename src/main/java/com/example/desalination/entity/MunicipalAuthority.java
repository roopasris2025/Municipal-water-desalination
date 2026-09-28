package com.example.desalination.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

/**
 * Master data: the municipal body that BUYS desalinated water from the plant.
 * Example: "Coimbatore Municipal Corporation".
 *
 * @Entity        -> tells JPA/Hibernate this class maps to a database table.
 * @Id            -> marks the primary key field.
 * @GeneratedValue-> the database auto-increments the id (AUTO_INCREMENT in MySQL).
 */
@Entity
@Table(name = "municipal_authority")
public class MunicipalAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    private String contactPerson;
    private String phone;
    private String email;
    private String address;

    public MunicipalAuthority() {
    }

    // ---- Getters and Setters ----
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getContactPerson() { return contactPerson; }
    public void setContactPerson(String contactPerson) { this.contactPerson = contactPerson; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
}
