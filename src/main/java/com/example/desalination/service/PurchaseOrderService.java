package com.example.desalination.service;

import com.example.desalination.entity.PurchaseOrder;
import com.example.desalination.repository.PurchaseOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * Handles Purchase Orders (plant buying RO membranes etc. from a Supplier).
 * totalAmount is calculated automatically as quantity * unitPrice so the
 * user never has to type it manually and it can never be wrong.
 */
@Service
public class PurchaseOrderService {

    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    public List<PurchaseOrder> findAll() {
        return purchaseOrderRepository.findAll();
    }

    public PurchaseOrder findById(Long id) {
        return purchaseOrderRepository.findById(id).orElse(null);
    }

    public PurchaseOrder save(PurchaseOrder po) {
        if (po.getQuantity() != null && po.getUnitPrice() != null) {
            BigDecimal total = po.getUnitPrice().multiply(BigDecimal.valueOf(po.getQuantity()));
            po.setTotalAmount(total);
        }
        return purchaseOrderRepository.save(po);
    }

    public void deleteById(Long id) {
        purchaseOrderRepository.deleteById(id);
    }
}
