package com.example.desalination.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.desalination.entity.CustomerInvoice;
import com.example.desalination.entity.Payment;
import com.example.desalination.entity.PaymentStatus;
import com.example.desalination.entity.PaymentType;
import com.example.desalination.entity.VendorBill;
import com.example.desalination.repository.CustomerInvoiceRepository;
import com.example.desalination.repository.PaymentRepository;
import com.example.desalination.repository.VendorBillRepository;

/**
 * Central place for all money movement (Simple Accounting module).
 *
 * - payVendorBill()   -> marks a VendorBill PAID and records the amount as an EXPENSE.
 * - receiveCustomerPayment() -> marks a CustomerInvoice PAID and records the amount as REVENUE.
 * - getTotalRevenue() / getTotalExpenses() / getNetProfit() are simply sums over the
 *   Payment table, filtered by paymentType - no separate ledger table needed.
 */
@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final VendorBillRepository vendorBillRepository;
    private final CustomerInvoiceRepository customerInvoiceRepository;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository,
                          VendorBillRepository vendorBillRepository,
                          CustomerInvoiceRepository customerInvoiceRepository) {
        this.paymentRepository = paymentRepository;
        this.vendorBillRepository = vendorBillRepository;
        this.customerInvoiceRepository = customerInvoiceRepository;
    }

    public List<Payment> findAll() {
        return paymentRepository.findAll();
    }

    /** Pays an existing Vendor Bill: creates a Payment row and flips the bill to PAID. */
    public void payVendorBill(Long vendorBillId, BigDecimal amount, String remarks) {
        VendorBill bill = vendorBillRepository.findById(vendorBillId).orElseThrow();
        bill.setPaymentStatus(PaymentStatus.PAID);
        vendorBillRepository.save(bill);

        Payment payment = new Payment();
        payment.setPaymentType(PaymentType.VENDOR_PAYMENT);
        payment.setReferenceId(vendorBillId);
        payment.setAmount(amount);
        payment.setPaymentDate(LocalDate.now());
        payment.setRemarks(remarks);
        paymentRepository.save(payment);
    }

    /** Receives payment for a Customer Invoice: creates a Payment row and flips the invoice to PAID. */
    public void receiveCustomerPayment(Long invoiceId, BigDecimal amount, String remarks) {
        CustomerInvoice invoice = customerInvoiceRepository.findById(invoiceId).orElseThrow();
        invoice.setPaymentStatus(PaymentStatus.PAID);
        customerInvoiceRepository.save(invoice);

        Payment payment = new Payment();
        payment.setPaymentType(PaymentType.CUSTOMER_PAYMENT);
        payment.setReferenceId(invoiceId);
        payment.setAmount(amount);
        payment.setPaymentDate(LocalDate.now());
        payment.setRemarks(remarks);
        paymentRepository.save(payment);
    }

    public BigDecimal getTotalRevenue() {
        return paymentRepository.findAll().stream()
                .filter(p -> p.getPaymentType() == PaymentType.CUSTOMER_PAYMENT)
                .map(Payment::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getTotalExpenses() {
        return paymentRepository.findAll().stream()
                .filter(p -> p.getPaymentType() == PaymentType.VENDOR_PAYMENT)
                .map(Payment::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getNetProfit() {
        return getTotalRevenue().subtract(getTotalExpenses());
    }
}
