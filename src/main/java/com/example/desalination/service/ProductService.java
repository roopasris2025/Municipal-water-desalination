package com.example.desalination.service;

import com.example.desalination.entity.Product;
import com.example.desalination.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Plain CRUD service for Product master data.
 * @Service marks this as a Spring-managed business-logic bean.
 * @Autowired lets Spring inject the repository automatically (constructor-less field injection,
 * kept simple as requested).
 */
@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}
