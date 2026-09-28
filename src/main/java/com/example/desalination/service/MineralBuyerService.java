package com.example.desalination.service;

import com.example.desalination.entity.MineralBuyer;
import com.example.desalination.repository.MineralBuyerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Plain CRUD service for MineralBuyer master data.
 * @Service marks this as a Spring-managed business-logic bean.
 * @Autowired lets Spring inject the repository automatically (constructor-less field injection,
 * kept simple as requested).
 */
@Service
public class MineralBuyerService {

    @Autowired
    private MineralBuyerRepository mineralBuyerRepository;

    public List<MineralBuyer> findAll() {
        return mineralBuyerRepository.findAll();
    }

    public MineralBuyer findById(Long id) {
        return mineralBuyerRepository.findById(id).orElse(null);
    }

    public MineralBuyer save(MineralBuyer mineralBuyer) {
        return mineralBuyerRepository.save(mineralBuyer);
    }

    public void deleteById(Long id) {
        mineralBuyerRepository.deleteById(id);
    }
}
