package com.example.desalination.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.desalination.service.DashboardService;

/**
 * Renders the home page (Dashboard) with the plant-wide summary numbers
 * described in the requirements: water produced/sold, minerals recovered,
 * revenue, expenses, current budget and recent alerts.
 */
@Controller
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping({"/", "/dashboard"})
    public String dashboard(Model model) {
        model.addAttribute("totalWaterProduced", dashboardService.getTotalWaterProduced());
        model.addAttribute("totalWaterSold", dashboardService.getTotalWaterSold());
        model.addAttribute("totalMineralRecovered", dashboardService.getTotalMineralRecovered());
        model.addAttribute("totalRevenue", dashboardService.getTotalRevenue());
        model.addAttribute("totalExpenses", dashboardService.getTotalExpenses());
        model.addAttribute("currentBudget", dashboardService.getCurrentBudget());
        model.addAttribute("budgetUsedPercentage", dashboardService.getBudgetUsedPercentage());
        model.addAttribute("recentAlerts", dashboardService.getRecentAlerts());
        return "dashboard";
    }
}
