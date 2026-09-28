package com.example.desalination.repository;

import com.example.desalination.entity.VendorBill;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Repository is not written explicitly here because JpaRepository
 * already implies it internally through Spring Data JPA's auto-configuration -
 * simply extending this interface is enough to get save(), findAll(),
 * findById(), deleteById() etc. for free, with MySQL under the hood.
 */
public interface VendorBillRepository extends JpaRepository<VendorBill, Long> {
}
