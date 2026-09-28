package com.example.desalination.restcontroller;

import com.example.desalination.entity.Budget;
import com.example.desalination.service.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Pure JSON API for Budget, meant to be tested with Postman - no HTML involved.
 * @RestController = @Controller + @ResponseBody, so every method's return value
 * is serialized straight to JSON instead of being resolved to a template name.
 */
@RestController
@RequestMapping("/api/desal/budgets")
public class BudgetRestController {

    @Autowired
    private BudgetService budgetService;

    @GetMapping
    public List<Budget> getAll() {
        return budgetService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Budget> getById(@PathVariable Long id) {
        Budget result = budgetService.findById(id);
        return result != null ? ResponseEntity.ok(result) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Budget> create(@RequestBody Budget budget) {
        Budget saved = budgetService.save(budget);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Budget> update(@PathVariable Long id, @RequestBody Budget budget) {
        if (budgetService.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        budget.setId(id);
        return ResponseEntity.ok(budgetService.save(budget));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (budgetService.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        budgetService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
