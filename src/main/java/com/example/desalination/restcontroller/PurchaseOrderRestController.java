package com.example.desalination.restcontroller;

import com.example.desalination.entity.PurchaseOrder;
import com.example.desalination.service.PurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/desal/purchase-orders")
public class PurchaseOrderRestController {

    @Autowired
    private PurchaseOrderService purchaseOrderService;

    @GetMapping
    public List<PurchaseOrder> getAll() {
        return purchaseOrderService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PurchaseOrder> getById(@PathVariable Long id) {
        PurchaseOrder result = purchaseOrderService.findById(id);
        return result != null ? ResponseEntity.ok(result) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<PurchaseOrder> create(@RequestBody PurchaseOrder purchaseOrder) {
        PurchaseOrder saved = purchaseOrderService.save(purchaseOrder);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PurchaseOrder> update(@PathVariable Long id, @RequestBody PurchaseOrder purchaseOrder) {
        if (purchaseOrderService.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        purchaseOrder.setId(id);
        return ResponseEntity.ok(purchaseOrderService.save(purchaseOrder));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (purchaseOrderService.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        purchaseOrderService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
