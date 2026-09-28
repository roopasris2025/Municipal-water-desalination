package com.example.desalination.controller;

import com.example.desalination.entity.MunicipalAuthority;
import com.example.desalination.service.MunicipalAuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @Controller (not @RestController) because every method here returns the NAME of a
 * Thymeleaf template (a String), which Spring MVC resolves to
 * src/main/resources/templates/authorities/xxx.html and renders as an HTML page.
 *
 * Browser flow for this module:
 *   GET  /authorities            -> list.html   (table of all records)
 *   GET  /authorities/new        -> form.html   (blank form)
 *   GET  /authorities/edit/{id}  -> form.html   (form pre-filled with existing data)
 *   POST /authorities/save       -> saves (insert or update) then redirects back to the list
 *   GET  /authorities/delete/{id}-> deletes the record then redirects back to the list
 */
@Controller
@RequestMapping("/authorities")
public class MunicipalAuthorityController {

    @Autowired
    private MunicipalAuthorityService municipalAuthorityService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("authorityList", municipalAuthorityService.findAll());
        return "authorities/list";
    }

    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("authority", new MunicipalAuthority());
        return "authorities/form";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("authority", municipalAuthorityService.findById(id));
        return "authorities/form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("authority") MunicipalAuthority municipalAuthority) {
        municipalAuthorityService.save(municipalAuthority);
        return "redirect:/authorities";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        municipalAuthorityService.deleteById(id);
        return "redirect:/authorities";
    }
}
