package com.example.desalination.restcontroller;

import com.example.desalination.entity.SalesOrder;
import com.example.desalination.service.SalesOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/desal/sales-orders")
public class SalesOrderRestController {

    @Autowired
    private SalesOrderService salesOrderService;

    @GetMapping
    public List<SalesOrder> getAll() {
        return salesOrderService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SalesOrder> getById(@PathVariable Long id) {
        SalesOrder result = salesOrderService.findById(id);
        return result != null ? ResponseEntity.ok(result) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<SalesOrder> create(@RequestBody SalesOrder salesOrder) {
        SalesOrder saved = salesOrderService.save(salesOrder);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SalesOrder> update(@PathVariable Long id, @RequestBody SalesOrder salesOrder) {
        if (salesOrderService.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        salesOrder.setId(id);
        return ResponseEntity.ok(salesOrderService.save(salesOrder));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (salesOrderService.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        salesOrderService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
