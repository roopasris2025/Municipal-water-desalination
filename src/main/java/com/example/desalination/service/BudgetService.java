package com.example.desalination.service;

import com.example.desalination.entity.Budget;
import com.example.desalination.repository.BudgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class BudgetService {

    @Autowired
    private BudgetRepository budgetRepository;

    public List<Budget> findAll() {
        return budgetRepository.findAll();
    }

    public Budget findById(Long id) {
        return budgetRepository.findById(id).orElse(null);
    }

    public Budget save(Budget budget) {
        return budgetRepository.save(budget);
    }

    public void deleteById(Long id) {
        budgetRepository.deleteById(id);
    }

    /** Remaining Amount = Planned Amount - Actual Amount */
    public BigDecimal getRemainingAmount(Budget budget) {
        if (budget.getPlannedAmount() == null || budget.getActualAmount() == null) {
            return BigDecimal.ZERO;
        }
        return budget.getPlannedAmount().subtract(budget.getActualAmount());
    }

    /** Budget Used % = (Actual Amount / Planned Amount) * 100 */
    public double getUsedPercentage(Budget budget) {
        if (budget.getPlannedAmount() == null || budget.getPlannedAmount().compareTo(BigDecimal.ZERO) == 0) {
            return 0.0;
        }
        return budget.getActualAmount()
                .divide(budget.getPlannedAmount(), 4, RoundingMode.HALF_UP)
                .multiply(BigDecimal.valueOf(100))
                .doubleValue();
    }

    /** The most recently created budget - used as the "current budget" shown on the Dashboard. */
    public Budget getLatestBudget() {
        List<Budget> all = budgetRepository.findAll();
        return all.isEmpty() ? null : all.get(all.size() - 1);
    }
}
