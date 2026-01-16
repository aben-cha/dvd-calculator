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

    public CartCalculator(DiscountCalculator discountCalculator) {
        this.discountCalculator = discountCalculator;
    }

    public double calculate(String input) {
        if (input == null || input.trim().isEmpty()) {
            return 0.0;
        }

        List<String> movieNames = parseInput(input);
        return calculateTotal(movieNames);
    }


    private List<String> parseInput(String input) {
        return Arrays.stream(input.split("\n"))
                .map(String::trim)
                .filter(line -> !line.isEmpty())
                .collect(Collectors.toList());
    }

    private double calculateTotal(List<String> movieNames) {
        List<String> bttfMovies = new ArrayList<>();
        List<String> otherMovies = new ArrayList<>();

        for (String movieName : movieNames) {
            MovieType type = MovieType.fromName(movieName);
            if (type == MovieType.BACK_TO_THE_FUTURE) {
                bttfMovies.add(movieName);
            } else {
                otherMovies.add(movieName);
            }
        }

        double bttfTotal = calculateBttfTotal(bttfMovies);

        double otherTotal = otherMovies.size() * MovieType.OTHER.getPrice();

        return bttfTotal + otherTotal;
    }

    private double calculateBttfTotal(List<String> bttfMovies) {
        if (bttfMovies.isEmpty()) {
            return 0.0;
        }

        double baseTotal = bttfMovies.size() * MovieType.BACK_TO_THE_FUTURE.getPrice();
        double discount = discountCalculator.calculateDiscount(bttfMovies);

        return baseTotal * discount;
    }

}
