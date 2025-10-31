package org.example.service.parser;

import org.example.entity.Expense;

import java.time.LocalDate;
import java.util.Optional;

public class CsvExpenseParser implements ExpenseParser {

    @Override
    public Optional<Expense> parse(String line) {

        if (line == null || line.startsWith("Amount")) {
            return Optional.empty();
        }

        String[] data = line.split(",", -1);

        if (data.length < 4) {
            return Optional.empty();
        }

        try {
            double amount = Double.parseDouble(data[0].trim());
            String description = data[1].trim();
            LocalDate date = LocalDate.parse(data[2].trim());
            String category = data[3].trim();

            return Optional.of(new Expense(amount, description, date, category));
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}