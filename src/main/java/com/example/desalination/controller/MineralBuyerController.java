package com.example.desalination.controller;

import com.example.desalination.entity.MineralBuyer;
import com.example.desalination.service.MineralBuyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @Controller (not @RestController) because every method here returns the NAME of a
 * Thymeleaf template (a String), which Spring MVC resolves to
 * src/main/resources/templates/buyers/xxx.html and renders as an HTML page.
 *
 * Browser flow for this module:
 *   GET  /buyers            -> list.html   (table of all records)
 *   GET  /buyers/new        -> form.html   (blank form)
 *   GET  /buyers/edit/{id}  -> form.html   (form pre-filled with existing data)
 *   POST /buyers/save       -> saves (insert or update) then redirects back to the list
 *   GET  /buyers/delete/{id}-> deletes the record then redirects back to the list
 */
@Controller
@RequestMapping("/buyers")
public class MineralBuyerController {

    @Autowired
    private MineralBuyerService mineralBuyerService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("buyerList", mineralBuyerService.findAll());
        return "buyers/list";
    }

    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("buyer", new MineralBuyer());
        return "buyers/form";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("buyer", mineralBuyerService.findById(id));
        return "buyers/form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("buyer") MineralBuyer mineralBuyer) {
        mineralBuyerService.save(mineralBuyer);
        return "redirect:/buyers";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        mineralBuyerService.deleteById(id);
        return "redirect:/buyers";
    }
}
