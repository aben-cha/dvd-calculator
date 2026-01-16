package com.ekinox.calculator.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Cart Calculator Tests")
public class CartCalculatorTest {
    private CartCalculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new CartCalculator();
    }

    @Test
    @DisplayName("Example 1: 3 different BTTF movies = 36€")
    void testExample1() {
        String input = "Back to the Future 1\nBack to the Future 2\nBack to the Future 3";
        double result = calculator.calculate(input);
        assertEquals(36.0, result, 0.01);
    }

    @Test
    @DisplayName("Example 2: 2 different BTTF movies = 27€")
    void testExample2() {
        String input = "Back to the Future 1\nBack to the Future 3";
        double result = calculator.calculate(input);
        assertEquals(27.0, result, 0.01);
    }

    @Test
    @DisplayName("Example 3: 1 BTTF movie = 15€")
    void testExample3() {
        String input = "Back to the Future 1";
        double result = calculator.calculate(input);
        assertEquals(15.0, result, 0.01);
    }

    @Test
    @DisplayName("Example 4: 3 different BTTF + 1 duplicate = 48€")
    void testExample4() {
        String input = "Back to the Future 1\nBack to the Future 2\nBack to the Future 3\nBack to the Future 2";
        double result = calculator.calculate(input);
        assertEquals(48.0, result, 0.01);
    }

    @Test
    @DisplayName("Example 5: 3 BTTF + 1 other movie = 56€")
    void testExample5() {
        String input = "Back to the Future 1\nBack to the Future 2\nBack to the Future 3\nLa chèvre";
        double result = calculator.calculate(input);
        assertEquals(56.0, result, 0.01);
    }

    @Test
    @DisplayName("Empty input returns 0")
    void testEmptyInput() {
        assertEquals(0.0, calculator.calculate(""), 0.01);
        assertEquals(0.0, calculator.calculate(null), 0.01);
    }

    @Test
    @DisplayName("Only other movies (no BTTF)")
    void testOnlyOtherMovies() {
        String input = "La chèvre\nLes Bronzés";
        assertEquals(40.0, calculator.calculate(input), 0.01);
    }

    @Test
    @DisplayName("2 same BTTF movies (no discount)")
    void testTwoSameMovies() {
        String input = "Back to the Future 1\nBack to the Future 1";
        assertEquals(30.0, calculator.calculate(input), 0.01);
    }

}
