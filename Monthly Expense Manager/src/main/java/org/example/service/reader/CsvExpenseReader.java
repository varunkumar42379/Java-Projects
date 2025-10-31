package org.example.service.reader;

import org.example.entity.Expense;
import org.example.service.parser.ExpenseParser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class CsvExpenseReader implements ExpenseReader {

    private final ExpenseParser parser;

    public CsvExpenseReader(ExpenseParser parser) {
        this.parser = parser;
    }

    @Override
    public List<Expense> read(File file) {

        List<Expense> expenses = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            String line;
            while ((line = reader.readLine()) != null) {
                parser.parse(line).ifPresent(expenses::add);
            }

        } catch (Exception e) {
            throw new RuntimeException("Error reading file: " + file.getName(), e);
        }

        return expenses;
    }
}