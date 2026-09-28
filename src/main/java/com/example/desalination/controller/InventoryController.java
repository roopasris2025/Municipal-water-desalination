package com.example.desalination.controller;

import com.example.desalination.service.InventoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/** Read-only stock view - quantities are changed automatically by Telemetry, Purchase and Sales flows. */
@Controller
@RequestMapping("/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("inventoryList", inventoryService.findAll());
        return "inventory/list";
    }
}
