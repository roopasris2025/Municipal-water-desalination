package com.example.desalination.controller;

import com.example.desalination.entity.PurchaseOrder;
import com.example.desalination.service.ProductService;
import com.example.desalination.service.PurchaseOrderService;
import com.example.desalination.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/purchase-orders")
public class PurchaseOrderController {

    @Autowired private PurchaseOrderService purchaseOrderService;
    @Autowired private SupplierService supplierService;
    @Autowired private ProductService productService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("poList", purchaseOrderService.findAll());
        return "purchaseorders/list";
    }

    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("po", new PurchaseOrder());
        model.addAttribute("suppliers", supplierService.findAll());
        model.addAttribute("products", productService.findAll());
        return "purchaseorders/form";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("po", purchaseOrderService.findById(id));
        model.addAttribute("suppliers", supplierService.findAll());
        model.addAttribute("products", productService.findAll());
        return "purchaseorders/form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("po") PurchaseOrder po) {
        purchaseOrderService.save(po);
        return "redirect:/purchase-orders";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        purchaseOrderService.deleteById(id);
        return "redirect:/purchase-orders";
    }
}
