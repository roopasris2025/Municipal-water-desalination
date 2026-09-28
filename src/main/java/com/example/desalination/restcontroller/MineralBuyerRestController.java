package com.example.desalination.restcontroller;

import com.example.desalination.entity.MineralBuyer;
import com.example.desalination.service.MineralBuyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Pure JSON API for MineralBuyer, meant to be tested with Postman - no HTML involved.
 * @RestController = @Controller + @ResponseBody, so every method's return value
 * is serialized straight to JSON instead of being resolved to a template name.
 */
@RestController
@RequestMapping("/api/desal/mineral-buyers")
public class MineralBuyerRestController {

    @Autowired
    private MineralBuyerService mineralBuyerService;

    @GetMapping
    public List<MineralBuyer> getAll() {
        return mineralBuyerService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MineralBuyer> getById(@PathVariable Long id) {
        MineralBuyer result = mineralBuyerService.findById(id);
        return result != null ? ResponseEntity.ok(result) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<MineralBuyer> create(@RequestBody MineralBuyer mineralBuyer) {
        MineralBuyer saved = mineralBuyerService.save(mineralBuyer);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MineralBuyer> update(@PathVariable Long id, @RequestBody MineralBuyer mineralBuyer) {
        if (mineralBuyerService.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        mineralBuyer.setId(id);
        return ResponseEntity.ok(mineralBuyerService.save(mineralBuyer));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (mineralBuyerService.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        mineralBuyerService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
