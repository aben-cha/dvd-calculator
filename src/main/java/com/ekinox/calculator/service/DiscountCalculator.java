package com.ekinox.calculator.service;

import java.util.List;

public class DiscountCalculator {
    private static final double NO_DISCOUNT = 1.0;
    private static final double TWO_MOVIES_DISCOUNT  = 0.9; // -10%
    private static final double THREE_MOVIES_DISCOUNT  = 0.8; // -20%

    public double calculateDiscount(List<String> bttfMovies) {
        long uniqueMovies = countUniqueMovies(bttfMovies);

        if (uniqueMovies >= 3) {
            return THREE_MOVIES_DISCOUNT;
        } else if (uniqueMovies >= 2) {
            return TWO_MOVIES_DISCOUNT;
        }
        return NO_DISCOUNT;
    }

    private long countUniqueMovies(List<String> movies) {
        return movies.stream()
                .distinct()
                .count();
    }
}
