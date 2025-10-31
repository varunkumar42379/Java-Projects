package org.example.service.report;

import org.example.entity.Expense;

import java.time.YearMonth;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ReportCalculator {

    public MonthlyReport calculate(List<Expense> expenses, YearMonth month) {

        List<Expense> monthly = expenses.stream()
                .filter(e -> YearMonth.from(e.getDate()).equals(month))
                .toList();

        double total = monthly.stream()
                .mapToDouble(Expense::getAmount)
                .sum();

        double avg = total / month.lengthOfMonth();

        Map<String, Double> byCategory = monthly.stream()
                .collect(Collectors.groupingBy(
                        e -> e.getCategory().isEmpty() ? "Uncategorized" : e.getCategory(),
                        Collectors.summingDouble(Expense::getAmount)
                ));

        Expense max = monthly.stream()
                .max(Comparator.comparingDouble(Expense::getAmount))
                .orElse(null);

        Expense min = monthly.stream()
                .min(Comparator.comparingDouble(Expense::getAmount))
                .orElse(null);

        return new MonthlyReport(total, avg, byCategory, max, min);
    }
}