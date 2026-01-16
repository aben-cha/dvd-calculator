package com.ekinox.calculator.model;

public enum MovieType {
    BACK_TO_THE_FUTURE(15.0),
    OTHER(20.0);

    private final double price;

    MovieType(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public static MovieType fromName(String movieName) {
        if (movieName == null) {
            return OTHER;
        }

        String normalized = movieName.trim().toLowerCase();
        if (normalized.startsWith("back to the future")) {
            return BACK_TO_THE_FUTURE;
        }
        return OTHER;
    }
}
