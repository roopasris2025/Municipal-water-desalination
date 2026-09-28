package com.example.desalination.service;

import com.example.desalination.entity.PaymentStatus;
import com.example.desalination.entity.VendorBill;
import com.example.desalination.repository.VendorBillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendorBillService {

    @Autowired
    private VendorBillRepository vendorBillRepository;

    public List<VendorBill> findAll() {
        return vendorBillRepository.findAll();
    }

    public VendorBill findById(Long id) {
        return vendorBillRepository.findById(id).orElse(null);
    }

    public VendorBill save(VendorBill bill) {
        if (bill.getPaymentStatus() == null) {
            bill.setPaymentStatus(PaymentStatus.PENDING);
        }
        return vendorBillRepository.save(bill);
    }

    public void deleteById(Long id) {
        vendorBillRepository.deleteById(id);
    }
}
