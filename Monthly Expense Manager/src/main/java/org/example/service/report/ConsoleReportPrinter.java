package org.example.service.report;

public class ConsoleReportPrinter implements ReportPrinter {

    @Override
    public void print(MonthlyReport report) {

        System.out.println("\n=== Monthly Expense Report ===");
        System.out.printf("Total Spent: ₹%.2f\n", report.getTotal());
        System.out.printf("Average Daily Expense: ₹%.2f\n", report.getAvgPerDay());

        System.out.println("Most Expensive: " + report.getMaxExpense());
        System.out.println("Least Expensive: " + report.getMinExpense());

        System.out.println("\nBy Category:");
        report.getCategoryTotals()
                .forEach((cat, amt) ->
                        System.out.printf(" - %-12s : ₹%.2f\n", cat, amt));
    }
}