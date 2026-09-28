package com.example.desalination.controller;

import com.example.desalination.entity.ROTrain;
import com.example.desalination.service.ROTrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @Controller (not @RestController) because every method here returns the NAME of a
 * Thymeleaf template (a String), which Spring MVC resolves to
 * src/main/resources/templates/trains/xxx.html and renders as an HTML page.
 *
 * Browser flow for this module:
 *   GET  /trains            -> list.html   (table of all records)
 *   GET  /trains/new        -> form.html   (blank form)
 *   GET  /trains/edit/{id}  -> form.html   (form pre-filled with existing data)
 *   POST /trains/save       -> saves (insert or update) then redirects back to the list
 *   GET  /trains/delete/{id}-> deletes the record then redirects back to the list
 */
@Controller
@RequestMapping("/trains")
public class ROTrainController {

    @Autowired
    private ROTrainService roTrainService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("trainList", roTrainService.findAll());
        return "trains/list";
    }

    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("train", new ROTrain());
        return "trains/form";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("train", roTrainService.findById(id));
        return "trains/form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("train") ROTrain roTrain) {
        roTrainService.save(roTrain);
        return "redirect:/trains";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        roTrainService.deleteById(id);
        return "redirect:/trains";
    }
}
