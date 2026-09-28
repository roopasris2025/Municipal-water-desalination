package com.example.desalination.service;

import com.example.desalination.entity.Alert;
import com.example.desalination.repository.AlertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Creates and lists system alerts.
 * Right now the only producer of alerts is TelemetryService (high pressure check),
 * but keeping it as its own service means any future module (e.g. low inventory)
 * can raise alerts the same way.
 */
@Service
public class AlertService {

    @Autowired
    private AlertRepository alertRepository;

    public void createAlert(String message, String severity) {
        Alert alert = new Alert();
        alert.setMessage(message);
        alert.setSeverity(severity);
        alert.setCreatedAt(LocalDateTime.now());
        alertRepository.save(alert);
    }

    public List<Alert> findAll() {
        return alertRepository.findAll();
    }

    public List<Alert> findRecent(int limit) {
        List<Alert> all = alertRepository.findAll();
        all.sort((a, b) -> b.getCreatedAt().compareTo(a.getCreatedAt()));
        return all.size() > limit ? all.subList(0, limit) : all;
    }

    public long countAll() {
        return alertRepository.count();
    }
}
