package com.example.desalination.restcontroller;

import com.example.desalination.entity.CustomerInvoice;
import com.example.desalination.service.CustomerInvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/desal/invoices")
public class CustomerInvoiceRestController {

    @Autowired
    private CustomerInvoiceService customerInvoiceService;

    @GetMapping
    public List<CustomerInvoice> getAll() {
        return customerInvoiceService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerInvoice> getById(@PathVariable Long id) {
        CustomerInvoice result = customerInvoiceService.findById(id);
        return result != null ? ResponseEntity.ok(result) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<CustomerInvoice> create(@RequestBody CustomerInvoice customerInvoice) {
        CustomerInvoice saved = customerInvoiceService.save(customerInvoice);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerInvoice> update(@PathVariable Long id, @RequestBody CustomerInvoice customerInvoice) {
        if (customerInvoiceService.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        customerInvoice.setId(id);
        return ResponseEntity.ok(customerInvoiceService.save(customerInvoice));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (customerInvoiceService.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        customerInvoiceService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
