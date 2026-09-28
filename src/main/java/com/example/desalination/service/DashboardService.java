package com.example.desalination.service;

import com.example.desalination.entity.Alert;
import com.example.desalination.entity.Budget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * A small "read model" service used only by the Dashboard page.
 * It does not own any repository itself - it just calls the other services
 * and packages the numbers the Dashboard template needs.
 */
@Service
public class DashboardService {

    @Autowired
    private TelemetryService telemetryService;

    @Autowired
    private SalesOrderService salesOrderService;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private BudgetService budgetService;

    @Autowired
    private AlertService alertService;

    public double getTotalWaterProduced() { return telemetryService.getTotalWaterProduced(); }

    public double getTotalWaterSold() { return salesOrderService.getTotalQuantitySold(); }

    public double getTotalMineralRecovered() { return telemetryService.getTotalMineralRecovered(); }

    public BigDecimal getTotalRevenue() { return paymentService.getTotalRevenue(); }

    public BigDecimal getTotalExpenses() { return paymentService.getTotalExpenses(); }

    public Budget getCurrentBudget() { return budgetService.getLatestBudget(); }

    public double getBudgetUsedPercentage() {
        Budget budget = getCurrentBudget();
        return budget == null ? 0.0 : budgetService.getUsedPercentage(budget);
    }

    public List<Alert> getRecentAlerts() { return alertService.findRecent(5); }
}
