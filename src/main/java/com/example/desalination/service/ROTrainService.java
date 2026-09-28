package com.example.desalination.service;

import com.example.desalination.entity.ROTrain;
import com.example.desalination.repository.ROTrainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Plain CRUD service for ROTrain master data.
 * @Service marks this as a Spring-managed business-logic bean.
 * @Autowired lets Spring inject the repository automatically (constructor-less field injection,
 * kept simple as requested).
 */
@Service
public class ROTrainService {

    @Autowired
    private ROTrainRepository roTrainRepository;

    public List<ROTrain> findAll() {
        return roTrainRepository.findAll();
    }

    public ROTrain findById(Long id) {
        return roTrainRepository.findById(id).orElse(null);
    }

    public ROTrain save(ROTrain roTrain) {
        return roTrainRepository.save(roTrain);
    }

    public void deleteById(Long id) {
        roTrainRepository.deleteById(id);
    }
}
