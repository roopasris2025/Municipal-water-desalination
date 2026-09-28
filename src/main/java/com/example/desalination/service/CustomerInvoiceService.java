package com.example.desalination.service;

import com.example.desalination.entity.CustomerInvoice;
import com.example.desalination.entity.PaymentStatus;
import com.example.desalination.repository.CustomerInvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerInvoiceService {

    @Autowired
    private CustomerInvoiceRepository customerInvoiceRepository;

    public List<CustomerInvoice> findAll() {
        return customerInvoiceRepository.findAll();
    }

    public CustomerInvoice findById(Long id) {
        return customerInvoiceRepository.findById(id).orElse(null);
    }

    public CustomerInvoice save(CustomerInvoice invoice) {
        if (invoice.getPaymentStatus() == null) {
            invoice.setPaymentStatus(PaymentStatus.PENDING);
        }
        return customerInvoiceRepository.save(invoice);
    }

    public void deleteById(Long id) {
        customerInvoiceRepository.deleteById(id);
    }
}
