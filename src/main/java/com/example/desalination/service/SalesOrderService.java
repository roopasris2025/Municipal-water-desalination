package com.example.desalination.service;

import com.example.desalination.entity.SalesOrder;
import com.example.desalination.repository.SalesOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * Handles Sales Orders (Municipal Authority buying water/minerals from the plant).
 * totalAmount = quantity * unitPrice, calculated automatically on save.
 */
@Service
public class SalesOrderService {

    @Autowired
    private SalesOrderRepository salesOrderRepository;

    public List<SalesOrder> findAll() {
        return salesOrderRepository.findAll();
    }

    public SalesOrder findById(Long id) {
        return salesOrderRepository.findById(id).orElse(null);
    }

    public SalesOrder save(SalesOrder so) {
        if (so.getQuantity() != null && so.getUnitPrice() != null) {
            BigDecimal total = so.getUnitPrice().multiply(BigDecimal.valueOf(so.getQuantity()));
            so.setTotalAmount(total);
        }
        return salesOrderRepository.save(so);
    }

    public void deleteById(Long id) {
        salesOrderRepository.deleteById(id);
    }

    /** Sum of totalAmount for every sales order - used on Dashboard as "Total water sold" proxy. */
    public double getTotalQuantitySold() {
        return salesOrderRepository.findAll().stream()
                .mapToDouble(so -> so.getQuantity() == null ? 0 : so.getQuantity())
                .sum();
    }
}
