package com.ekinox.calculator.service;

import com.ekinox.calculator.model.MovieType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CartCalculator {
    private final DiscountCalculator discountCalculator;

    public CartCalculator() {
        this.discountCalculator = new DiscountCalculator();
    }

    // Constructor for testing (dependency injection)
    public CartCalculator(DiscountCalculator discountCalculator) {
        this.discountCalculator = discountCalculator;
    }

    // Calculate total price from input string
    public double calculate(String input) {
        if (input == null) {
            throw new IllegalArgumentException("Input cannot be null");
        }
        if (input.trim().isEmpty()) {
            return 0.0;
        }

        try {
            List<String> movieNames = parseInput(input);
            return calculateTotal(movieNames);
        } catch (Exception e) {
            throw new RuntimeException("Error calculating cart total", e);
        }
    }


    // Parse input string into list of movie names
    private List<String> parseInput(String input) {
        return Arrays.stream(input.split("\n"))
                .map(String::trim)
                .filter(line -> !line.isEmpty())
                .collect(Collectors.toList());
    }

    // Calculate total price for all movies
    private double calculateTotal(List<String> movieNames) {
        List<String> bttfMovies = new ArrayList<>();
        List<String> otherMovies = new ArrayList<>();

        // Separate BTTF from other movies
        for (String movieName : movieNames) {
            MovieType type = MovieType.fromName(movieName);
            if (type == MovieType.BACK_TO_THE_FUTURE) {
                bttfMovies.add(movieName);
            } else {
                otherMovies.add(movieName);
            }
        }

        // Calculate BTTF total with discount
        double bttfTotal = calculateBttfTotal(bttfMovies);

        // Calculate other movies total (no discount)
        double otherTotal = otherMovies.size() * MovieType.OTHER.getPrice();

        return bttfTotal + otherTotal;
    }

    // Calculate total for BTTF movies with discount applied
    private double calculateBttfTotal(List<String> bttfMovies) {
        if (bttfMovies.isEmpty()) {
            return 0.0;
        }

        double baseTotal = bttfMovies.size() * MovieType.BACK_TO_THE_FUTURE.getPrice();
        double discount = discountCalculator.calculateDiscount(bttfMovies);

        return baseTotal * discount;
    }

}
