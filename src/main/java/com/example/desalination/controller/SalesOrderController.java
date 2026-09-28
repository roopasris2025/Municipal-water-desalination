package com.example.desalination.controller;

import com.example.desalination.entity.SalesOrder;
import com.example.desalination.service.MunicipalAuthorityService;
import com.example.desalination.service.ProductService;
import com.example.desalination.service.SalesOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/sales-orders")
public class SalesOrderController {

    @Autowired private SalesOrderService salesOrderService;
    @Autowired private MunicipalAuthorityService municipalAuthorityService;
    @Autowired private ProductService productService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("soList", salesOrderService.findAll());
        return "salesorders/list";
    }

    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("so", new SalesOrder());
        model.addAttribute("customers", municipalAuthorityService.findAll());
        model.addAttribute("products", productService.findAll());
        return "salesorders/form";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("so", salesOrderService.findById(id));
        model.addAttribute("customers", municipalAuthorityService.findAll());
        model.addAttribute("products", productService.findAll());
        return "salesorders/form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("so") SalesOrder so) {
        salesOrderService.save(so);
        return "redirect:/sales-orders";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        salesOrderService.deleteById(id);
        return "redirect:/sales-orders";
    }
}
