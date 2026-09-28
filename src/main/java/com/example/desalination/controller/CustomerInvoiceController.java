package com.example.desalination.controller;

import com.example.desalination.entity.CustomerInvoice;
import com.example.desalination.service.CustomerInvoiceService;
import com.example.desalination.service.MunicipalAuthorityService;
import com.example.desalination.service.PaymentService;
import com.example.desalination.service.SalesOrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Customer Invoice flow: SalesOrder -> CustomerInvoice -> Payment.
 * "/pay/{id}" is where receiving payment marks the invoice PAID and books revenue.
 */
@Controller
@RequestMapping("/invoices")
public class CustomerInvoiceController {

    private final CustomerInvoiceService customerInvoiceService;
    private final MunicipalAuthorityService municipalAuthorityService;
    private final SalesOrderService salesOrderService;
    private final PaymentService paymentService;

    public CustomerInvoiceController(CustomerInvoiceService customerInvoiceService,
                                     MunicipalAuthorityService municipalAuthorityService,
                                     SalesOrderService salesOrderService,
                                     PaymentService paymentService) {
        this.customerInvoiceService = customerInvoiceService;
        this.municipalAuthorityService = municipalAuthorityService;
        this.salesOrderService = salesOrderService;
        this.paymentService = paymentService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("invoiceList", customerInvoiceService.findAll());
        return "invoices/list";
    }

    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("invoice", new CustomerInvoice());
        model.addAttribute("customers", municipalAuthorityService.findAll());
        model.addAttribute("salesOrders", salesOrderService.findAll());
        return "invoices/form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("invoice") CustomerInvoice invoice) {
        customerInvoiceService.save(invoice);
        return "redirect:/invoices";
    }

    @GetMapping("/pay/{id}")
    public String pay(@PathVariable Long id) {
        CustomerInvoice invoice = customerInvoiceService.findById(id);
        if (invoice != null) {
            paymentService.receiveCustomerPayment(id, invoice.getAmount(), "Payment for invoice " + invoice.getInvoiceNumber());
        }
        return "redirect:/invoices";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        customerInvoiceService.deleteById(id);
        return "redirect:/invoices";
    }
}
