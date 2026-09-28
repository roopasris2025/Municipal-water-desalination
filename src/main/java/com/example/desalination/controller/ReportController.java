package com.example.desalination.controller;

import com.example.desalination.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/** Builds the three report tabs (Operational, Profit & Loss, Budget) on one page. */
@Controller
@RequestMapping("/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping
    public String reports(Model model) {
        model.addAttribute("totalWaterProduced", reportService.getTotalWaterProduced());
        model.addAttribute("totalWaterSold", reportService.getTotalWaterSold());
        model.addAttribute("totalMineralRecovered", reportService.getTotalMineralRecovered());
        model.addAttribute("numberOfTrains", reportService.getNumberOfTrains());
        model.addAttribute("numberOfAlerts", reportService.getNumberOfAlerts());

        model.addAttribute("totalRevenue", reportService.getTotalRevenue());
        model.addAttribute("totalExpenses", reportService.getTotalExpenses());
        model.addAttribute("netProfit", reportService.getNetProfit());

        var latestBudget = reportService.getLatestBudget();
        model.addAttribute("latestBudget", latestBudget);
        if (latestBudget != null) {
            model.addAttribute("remainingAmount", reportService.getRemainingAmount(latestBudget));
            model.addAttribute("usedPercentage", reportService.getUsedPercentage(latestBudget));
        }
        return "reports/reports";
    }
}
