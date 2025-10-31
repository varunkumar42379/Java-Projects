package org.example.service.report;

import org.example.entity.Expense;

import java.util.Map;

public class MonthlyReport {

    private final double total;
    private final double avgPerDay;
    private final Map<String, Double> categoryTotals;
    private final Expense maxExpense;
    private final Expense minExpense;

    public MonthlyReport(double total,
                         double avgPerDay,
                         Map<String, Double> categoryTotals,
                         Expense maxExpense,
                         Expense minExpense) {

        this.total = total;
        this.avgPerDay = avgPerDay;
        this.categoryTotals = categoryTotals;
        this.maxExpense = maxExpense;
        this.minExpense = minExpense;
    }

    public double getTotal() { return total; }
    public double getAvgPerDay() { return avgPerDay; }
    public Map<String, Double> getCategoryTotals() { return categoryTotals; }
    public Expense getMaxExpense() { return maxExpense; }
    public Expense getMinExpense() { return minExpense; }
}