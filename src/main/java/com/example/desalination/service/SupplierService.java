package com.example.desalination.service;

import com.example.desalination.entity.Supplier;
import com.example.desalination.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Plain CRUD service for Supplier master data.
 * @Service marks this as a Spring-managed business-logic bean.
 * @Autowired lets Spring inject the repository automatically (constructor-less field injection,
 * kept simple as requested).
 */
@Service
public class SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    public List<Supplier> findAll() {
        return supplierRepository.findAll();
    }

    public Supplier findById(Long id) {
        return supplierRepository.findById(id).orElse(null);
    }

    public Supplier save(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    public void deleteById(Long id) {
        supplierRepository.deleteById(id);
    }
}
