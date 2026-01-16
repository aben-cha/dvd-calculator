package com.ekinox.calculator;

import com.ekinox.calculator.service.CartCalculator;

import java.util.Scanner;

public class Main {
     public static void main(String[] args) {
        System.out.println("Hello and welcome!");
         CartCalculator calculator = new CartCalculator();

         System.out.println("=== DVD Price Calculator ===");
         System.out.println("Ekinox Technical Challenge Solution\n");

         runExample(calculator,
                 "Back to the Future 1\nBack to the Future 2\nBack to the Future 3",
                 36.0, "Example 1: 3 different BTTF");

         runExample(calculator,
                 "Back to the Future 1\nBack to the Future 3",
                 27.0, "Example 2: 2 different BTTF");

         runExample(calculator,
                 "Back to the Future 1",
                 15.0, "Example 3: 1 BTTF");

         runExample(calculator,
                 "Back to the Future 1\nBack to the Future 2\nBack to the Future 3\nBack to the Future 2",
                 48.0, "Example 4: 4 DVDs (3 different)");

         runExample(calculator,
                 "Back to the Future 1\nBack to the Future 2\nBack to the Future 3\nLa chèvre",
                 56.0, "Example 5: 3 BTTF + 1 other");

     }

    private static void runExample(CartCalculator calculator,
                                   String input,
                                   double expected,
                                   String description) {
        double result = calculator.calculate(input);
        boolean passed = Math.abs(result - expected) < 0.01;
        String status = passed ? "✓ PASS" : "✗ FAIL";

        System.out.printf("%s - %s\n", status, description);
        System.out.printf("  Expected: %.0f€, Got: %.0f€\n\n", expected, result);
    }

    private static double readInputAndCalculate(Scanner scanner, CartCalculator calculator) {
        System.out.println("Enter movies (one per line), empty line to calculate:");
        StringBuilder input = new StringBuilder();
        String line;

        while (!(line = scanner.nextLine()).isEmpty()) {
            input.append(line).append("\n");
        }

        return calculator.calculate(input.toString());
    }

}
