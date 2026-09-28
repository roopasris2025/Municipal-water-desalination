package com.example.desalination.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * A single money-movement record. One Payment either:
 *  - pays a VendorBill  (type = VENDOR_PAYMENT, becomes an EXPENSE), or
 *  - settles a CustomerInvoice (type = CUSTOMER_PAYMENT, becomes REVENUE).
 * We keep both cases in one simple table instead of building a full ledger.
 */
@Entity
@Table(name = "payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    private Long referenceId;      // id of the VendorBill or CustomerInvoice being paid
    private BigDecimal amount;
    private LocalDate paymentDate;
    private String remarks;

    public Payment() {
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public PaymentType getPaymentType() { return paymentType; }
    public void setPaymentType(PaymentType paymentType) { this.paymentType = paymentType; }

    public Long getReferenceId() { return referenceId; }
    public void setReferenceId(Long referenceId) { this.referenceId = referenceId; }

    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }

    public LocalDate getPaymentDate() { return paymentDate; }
    public void setPaymentDate(LocalDate paymentDate) { this.paymentDate = paymentDate; }

    public String getRemarks() { return remarks; }
    public void setRemarks(String remarks) { this.remarks = remarks; }
}
