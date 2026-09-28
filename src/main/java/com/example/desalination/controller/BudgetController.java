package com.example.desalination.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.desalination.entity.Budget;
import com.example.desalination.service.BudgetService;

@Controller
@RequestMapping("/budget")
public class BudgetController {

    private final BudgetService budgetService;

    public BudgetController(BudgetService budgetService) {
        this.budgetService = budgetService;
    }

    @GetMapping
    public String list(Model model) {
        java.util.List<Budget> budgets = budgetService.findAll();
        Map<Long, Double> usedPercentMap = new HashMap<>();
        for (Budget b : budgets) {
            usedPercentMap.put(b.getId(), budgetService.getUsedPercentage(b));
        }
        model.addAttribute("budgetList", budgets);
        model.addAttribute("usedPercentMap", usedPercentMap);
        return "budget/list";
    }

    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("budget", new Budget());
        return "budget/form";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("budget", budgetService.findById(id));
        return "budget/form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("budget") Budget budget) {
        budgetService.save(budget);
        return "redirect:/budget";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        budgetService.deleteById(id);
        return "redirect:/budget";
    }
}
