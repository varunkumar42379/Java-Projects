package org.example.service.reader;

import org.example.entity.Expense;

import java.io.File;
import java.util.List;

public interface ExpenseReader {
    List<Expense> read(File file);
}