package com.example.desalination.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

/**
 * A yearly financial plan the plant is tracked against.
 * remaining amount and used % are calculated on the fly in the service layer,
 * not stored, so they are always accurate.
 */
@Entity
@Table(name = "budget")
public class Budget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String budgetName;
    private BigDecimal plannedAmount;
    private BigDecimal actualAmount = BigDecimal.ZERO;
    private String financialYear;

    public Budget() {
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getBudgetName() { return budgetName; }
    public void setBudgetName(String budgetName) { this.budgetName = budgetName; }

    public BigDecimal getPlannedAmount() { return plannedAmount; }
    public void setPlannedAmount(BigDecimal plannedAmount) { this.plannedAmount = plannedAmount; }

    public BigDecimal getActualAmount() { return actualAmount; }
    public void setActualAmount(BigDecimal actualAmount) { this.actualAmount = actualAmount; }

    public String getFinancialYear() { return financialYear; }
    public void setFinancialYear(String financialYear) { this.financialYear = financialYear; }
}
