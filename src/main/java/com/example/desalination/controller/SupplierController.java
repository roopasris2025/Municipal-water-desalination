package com.example.desalination.controller;

import com.example.desalination.entity.Supplier;
import com.example.desalination.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @Controller (not @RestController) because every method here returns the NAME of a
 * Thymeleaf template (a String), which Spring MVC resolves to
 * src/main/resources/templates/suppliers/xxx.html and renders as an HTML page.
 *
 * Browser flow for this module:
 *   GET  /suppliers            -> list.html   (table of all records)
 *   GET  /suppliers/new        -> form.html   (blank form)
 *   GET  /suppliers/edit/{id}  -> form.html   (form pre-filled with existing data)
 *   POST /suppliers/save       -> saves (insert or update) then redirects back to the list
 *   GET  /suppliers/delete/{id}-> deletes the record then redirects back to the list
 */
@Controller
@RequestMapping("/suppliers")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("supplierList", supplierService.findAll());
        return "suppliers/list";
    }

    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("supplier", new Supplier());
        return "suppliers/form";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("supplier", supplierService.findById(id));
        return "suppliers/form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("supplier") Supplier supplier) {
        supplierService.save(supplier);
        return "redirect:/suppliers";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        supplierService.deleteById(id);
        return "redirect:/suppliers";
    }
}
