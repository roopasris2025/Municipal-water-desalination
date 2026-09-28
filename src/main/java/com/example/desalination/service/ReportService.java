package com.example.desalination.service;

import com.example.desalination.entity.Budget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * Builds the numbers shown on the three Reports tabs:
 * Operational Report, Profit & Loss, Budget Report.
 * Like DashboardService, this is a pure aggregator - no repository of its own.
 */
@Service
public class ReportService {

    @Autowired private TelemetryService telemetryService;
    @Autowired private SalesOrderService salesOrderService;
    @Autowired private ROTrainService roTrainService;
    @Autowired private AlertService alertService;
    @Autowired private PaymentService paymentService;
    @Autowired private BudgetService budgetService;

    // ---- Operational Report ----
    public double getTotalWaterProduced() { return telemetryService.getTotalWaterProduced(); }
    public double getTotalWaterSold() { return salesOrderService.getTotalQuantitySold(); }
    public double getTotalMineralRecovered() { return telemetryService.getTotalMineralRecovered(); }
    public int getNumberOfTrains() { return roTrainService.findAll().size(); }
    public long getNumberOfAlerts() { return alertService.countAll(); }

    // ---- Profit & Loss ----
    public BigDecimal getTotalRevenue() { return paymentService.getTotalRevenue(); }
    public BigDecimal getTotalExpenses() { return paymentService.getTotalExpenses(); }
    public BigDecimal getNetProfit() { return paymentService.getNetProfit(); }

    // ---- Budget Report (uses the latest budget) ----
    public Budget getLatestBudget() { return budgetService.getLatestBudget(); }
    public BigDecimal getRemainingAmount(Budget budget) { return budgetService.getRemainingAmount(budget); }
    public double getUsedPercentage(Budget budget) { return budgetService.getUsedPercentage(budget); }
}
