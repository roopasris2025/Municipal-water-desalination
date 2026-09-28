package com.example.desalination.controller;

import com.example.desalination.entity.Product;
import com.example.desalination.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @Controller (not @RestController) because every method here returns the NAME of a
 * Thymeleaf template (a String), which Spring MVC resolves to
 * src/main/resources/templates/products/xxx.html and renders as an HTML page.
 *
 * Browser flow for this module:
 *   GET  /products            -> list.html   (table of all records)
 *   GET  /products/new        -> form.html   (blank form)
 *   GET  /products/edit/{id}  -> form.html   (form pre-filled with existing data)
 *   POST /products/save       -> saves (insert or update) then redirects back to the list
 *   GET  /products/delete/{id}-> deletes the record then redirects back to the list
 */
@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("productList", productService.findAll());
        return "products/list";
    }

    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("product", new Product());
        return "products/form";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("product", productService.findById(id));
        return "products/form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("product") Product product) {
        productService.save(product);
        return "redirect:/products";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        productService.deleteById(id);
        return "redirect:/products";
    }
}
