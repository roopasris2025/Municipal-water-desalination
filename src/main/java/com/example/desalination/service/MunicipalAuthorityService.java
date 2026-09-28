package com.example.desalination.service;

import com.example.desalination.entity.MunicipalAuthority;
import com.example.desalination.repository.MunicipalAuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Plain CRUD service for MunicipalAuthority master data.
 * @Service marks this as a Spring-managed business-logic bean.
 * @Autowired lets Spring inject the repository automatically (constructor-less field injection,
 * kept simple as requested).
 */
@Service
public class MunicipalAuthorityService {

    @Autowired
    private MunicipalAuthorityRepository municipalAuthorityRepository;

    public List<MunicipalAuthority> findAll() {
        return municipalAuthorityRepository.findAll();
    }

    public MunicipalAuthority findById(Long id) {
        return municipalAuthorityRepository.findById(id).orElse(null);
    }

    public MunicipalAuthority save(MunicipalAuthority municipalAuthority) {
        return municipalAuthorityRepository.save(municipalAuthority);
    }

    public void deleteById(Long id) {
        municipalAuthorityRepository.deleteById(id);
    }
}
