package org.example;

import org.example.database.DataBaseConnection;
import org.example.entity.Expense;
import org.example.service.ExpenseService;
import org.example.service.parser.CsvExpenseParser;
import org.example.service.reader.CsvExpenseReader;
import org.example.service.reader.ExpenseReader;
import org.example.service.report.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.YearMonth;
import java.util.List;
import java.util.Properties;

public class ExpenseManagerApp {

    public static void main(String[] args) {

        try {
            // STEP 1: Read properties file path from command line argument
            Properties properties = readProperties(args[0]);

            // STEP 2: Create ExpenseReader object
            // CsvExpenseReader needs CsvExpenseParser → dependency injection
            ExpenseReader reader =
                    new CsvExpenseReader(new CsvExpenseParser());

            // STEP 3: Inject reader into service
            // Service will use reader to fetch expenses
            ExpenseService service =
                    new ExpenseService(reader);

            // STEP 4: Read all expenses from given directory path
            List<Expense> expenses =
                    service.readDirectory(properties.getProperty("input.path"));

            // STEP 5: Get report month from properties file
            String monthValue = properties.getProperty("report.month");

            // STEP 6: Validate month value
            if (monthValue == null) {
                throw new IllegalArgumentException("report.month is missing in properties file");
            }

            // STEP 7: Convert String → YearMonth (e.g., "2024-03")
            YearMonth selectedMonth = YearMonth.parse(monthValue);

            // STEP 8: Create calculator to process expenses
            ReportCalculator calculator = new ReportCalculator();

            // STEP 9: Generate monthly report using expenses + selected month
            MonthlyReport report =
                    calculator.calculate(expenses, selectedMonth);

            // STEP 10: Choose how to print report (Console implementation)
            ReportPrinter printer =
                    new ConsoleReportPrinter();

            // STEP 11: Print the report
            printer.print(report);

        } catch (Exception e) {
            // STEP 12: Handle any exception in the flow
            e.printStackTrace();
        }

        // STEP 13: Get database connection (just printing here)
        System.out.println(DataBaseConnection.connection());
    }

    private static Properties readProperties(String path) {

        // STEP A: Create Properties object
        Properties properties = new Properties();

        // STEP B: Read file using FileInputStream
        try (FileInputStream fis = new FileInputStream(path)) {

            // STEP C: Load key-value pairs into properties object
            properties.load(fis);

        } catch (IOException e) {

            // STEP D: Throw runtime exception if file reading fails
            throw new RuntimeException("Failed to read properties file", e);
        }

        // STEP E: Return loaded properties
        return properties;
    }
}