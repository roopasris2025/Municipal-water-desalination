package com.example.desalination.restcontroller;

import com.example.desalination.entity.ROTrain;
import com.example.desalination.service.ROTrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Pure JSON API for ROTrain, meant to be tested with Postman - no HTML involved.
 * @RestController = @Controller + @ResponseBody, so every method's return value
 * is serialized straight to JSON instead of being resolved to a template name.
 */
@RestController
@RequestMapping("/api/desal/trains")
public class ROTrainRestController {

    @Autowired
    private ROTrainService roTrainService;

    @GetMapping
    public List<ROTrain> getAll() {
        return roTrainService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ROTrain> getById(@PathVariable Long id) {
        ROTrain result = roTrainService.findById(id);
        return result != null ? ResponseEntity.ok(result) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<ROTrain> create(@RequestBody ROTrain roTrain) {
        ROTrain saved = roTrainService.save(roTrain);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ROTrain> update(@PathVariable Long id, @RequestBody ROTrain roTrain) {
        if (roTrainService.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        roTrain.setId(id);
        return ResponseEntity.ok(roTrainService.save(roTrain));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (roTrainService.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        roTrainService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
