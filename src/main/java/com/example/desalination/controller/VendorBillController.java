package com.example.desalination.controller;

import com.example.desalination.entity.VendorBill;
import com.example.desalination.service.PaymentService;
import com.example.desalination.service.PurchaseOrderService;
import com.example.desalination.service.SupplierService;
import com.example.desalination.service.VendorBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Vendor Bill flow: PurchaseOrder -> VendorBill -> Payment.
 * The extra "/pay/{id}" endpoint is where PaymentService is called - clicking
 * "Pay" on a PENDING bill marks it PAID and books the amount as an expense.
 */
@Controller
@RequestMapping("/vendor-bills")
public class VendorBillController {

    @Autowired private VendorBillService vendorBillService;
    @Autowired private SupplierService supplierService;
    @Autowired private PurchaseOrderService purchaseOrderService;
    @Autowired private PaymentService paymentService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("billList", vendorBillService.findAll());
        return "vendorbills/list";
    }

    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("bill", new VendorBill());
        model.addAttribute("suppliers", supplierService.findAll());
        model.addAttribute("purchaseOrders", purchaseOrderService.findAll());
        return "vendorbills/form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("bill") VendorBill bill) {
        vendorBillService.save(bill);
        return "redirect:/vendor-bills";
    }

    @GetMapping("/pay/{id}")
    public String pay(@PathVariable Long id) {
        VendorBill bill = vendorBillService.findById(id);
        if (bill != null) {
            paymentService.payVendorBill(id, bill.getAmount(), "Payment for bill " + bill.getBillNumber());
        }
        return "redirect:/vendor-bills";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        vendorBillService.deleteById(id);
        return "redirect:/vendor-bills";
    }
}
