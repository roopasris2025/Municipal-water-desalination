package com.example.desalination.restcontroller;

import com.example.desalination.entity.Telemetry;
import com.example.desalination.service.TelemetryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * JSON API for telemetry. POST/PUT go through submitTelemetry() so that
 * sending a reading via Postman triggers the same inventory-update and
 * high-pressure-alert logic as submitting it through the web form.
 */
@RestController
@RequestMapping("/api/desal/telemetry")
public class TelemetryRestController {

    @Autowired
    private TelemetryService telemetryService;

    @GetMapping
    public List<Telemetry> getAll() {
        return telemetryService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Telemetry> getById(@PathVariable Long id) {
        Telemetry result = telemetryService.findById(id);
        return result != null ? ResponseEntity.ok(result) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Telemetry> create(@RequestBody Telemetry telemetry) {
        Telemetry saved = telemetryService.submitTelemetry(telemetry);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Telemetry> update(@PathVariable Long id, @RequestBody Telemetry telemetry) {
        if (telemetryService.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        telemetry.setId(id);
        return ResponseEntity.ok(telemetryService.submitTelemetry(telemetry));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (telemetryService.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        telemetryService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
