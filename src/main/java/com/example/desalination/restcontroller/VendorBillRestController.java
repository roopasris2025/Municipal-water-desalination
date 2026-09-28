package com.example.desalination.restcontroller;

import com.example.desalination.entity.VendorBill;
import com.example.desalination.service.VendorBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/desal/vendor-bills")
public class VendorBillRestController {

    @Autowired
    private VendorBillService vendorBillService;

    @GetMapping
    public List<VendorBill> getAll() {
        return vendorBillService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<VendorBill> getById(@PathVariable Long id) {
        VendorBill result = vendorBillService.findById(id);
        return result != null ? ResponseEntity.ok(result) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<VendorBill> create(@RequestBody VendorBill vendorBill) {
        VendorBill saved = vendorBillService.save(vendorBill);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VendorBill> update(@PathVariable Long id, @RequestBody VendorBill vendorBill) {
        if (vendorBillService.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        vendorBill.setId(id);
        return ResponseEntity.ok(vendorBillService.save(vendorBill));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (vendorBillService.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        vendorBillService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
