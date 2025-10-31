package org.example.service;

import org.example.entity.Expense;
import org.example.service.reader.ExpenseReader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ExpenseService {

    private final ExpenseReader reader;

    public ExpenseService(ExpenseReader reader) {
        this.reader = reader;
    }

    public List<Expense> readDirectory(String path) {

        File folder = new File(path);
        File[] files = folder.listFiles((dir, name) -> name.endsWith(".csv"));

        if (files == null || files.length == 0) {
            return new ArrayList<>();
        }

        ExecutorService executor =
                Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        List<Future<List<Expense>>> futures = new ArrayList<>();

        for (File file : files) {
            futures.add(executor.submit(() -> reader.read(file)));
        }

        List<Expense> allExpenses = new ArrayList<>();

        for (Future<List<Expense>> future : futures) {
            try {
                allExpenses.addAll(future.get());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        executor.shutdown();

        return allExpenses;
    }
}