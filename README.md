# DVD Calculator
A Java application that calculates the total price of a DVD shopping cart with special discounts for the "Back to the Future" trilogy.

## 📋 Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Technical Stack](#technical-stack)
- [Project Structure](#project-structure)
- [Installation](#installation)
- [Usage](#usage)
- [Running Tests](#running-tests)
- [Business Rules](#business-rules)
- [Examples](#examples)
- [Design Decisions](#design-decisions)

---

## 🎯 Overview

This project implements a pricing calculator for a DVD store with a promotional campaign for the "Back to the Future" trilogy. The application calculates the total price based on the movies in the cart and applies discounts when multiple different episodes are purchased.

## ✨ Features

- ✅ Calculate total price for a shopping cart
- ✅ Apply progressive discounts for BTTF trilogy purchases
- ✅ Support for other movies (non-BTTF)
- ✅ Case-insensitive movie name handling
- ✅ Robust input parsing with whitespace handling
- ✅ Comprehensive test coverage


## 🛠️ Technical Stack

- **Java**: 17 (LTS)
- **Build Tool**: Maven 3.x
- **Testing Framework**: JUnit 5
- **Design Patterns**: Strategy Pattern, Dependency Injection
- **Code Style**: Clean Code principles, SOLID principles

## 📁 Project Structure
```
dvd-calculator/
├── src/
│   ├── main/
│   │   └── java/com/ekinox/calculator/
│   │       ├── Main.java                    # Application entry point
│   │       ├── model/
│   │       │   └── MovieType.java           # Movie type enumeration
│   │       └── service/
│   │           ├── CartCalculator.java      # Main calculation logic
│   │           └── DiscountCalculator.java  # Discount calculation
│   └── test/
│       └── java/com/ekinox/calculator/
│           └── service/
│               └── CartCalculatorTest.java  # Comprehensive tests
├── pom.xml                                  # Maven configuration
└── README.md                                # This file
```

## 🚀 Installation

### Prerequisites

- Java 17 or higher
- Maven 3.6 or higher

### Clone the Repository
```bash
git clone https://github.com/aben-cha/dvd-calculator
cd dvd-calculator
```

### Build the Project
```bash
mvn clean install
```

## 💻 Usage

### Running the Demo Application

The `Main.java` class demonstrates all examples from the technical requirements:
```bash
mvn exec:java -Dexec.mainClass="com.ekinox.calculator.Main"
```

**Expected Output:**
```
=== DVD Price Calculator ===
Ekinox Technical Challenge Solution

✓ PASS - Example 1: 3 different BTTF
  Expected: 36€, Got: 36€

✓ PASS - Example 2: 2 different BTTF
  Expected: 27€, Got: 27€

✓ PASS - Example 3: 1 BTTF
  Expected: 15€, Got: 15€

✓ PASS - Example 4: 4 DVDs (3 different)
  Expected: 48€, Got: 48€

✓ PASS - Example 5: 3 BTTF + 1 other
  Expected: 56€, Got: 56€
```

### Programmatic Usage
```java
import com.ekinox.calculator.service.CartCalculator;

public class Example {
    public static void main(String[] args) {
        CartCalculator calculator = new CartCalculator();
        
        String cart = "Back to the Future 1\nBack to the Future 2\nBack to the Future 3";
        double total = calculator.calculate(cart);
        
        System.out.println("Total: " + total + "€"); // Output: 36.0€
    }
}
```

## 🧪 Running Tests

### Run All Tests
```bash
mvn test
```

### Run with Coverage Report
```bash
mvn clean test
```

### Test Results

The project includes comprehensive tests covering:
- ✅ All business rule examples
- ✅ Edge cases (null, empty input)
- ✅ Case insensitivity
- ✅ Whitespace handling
- ✅ Mixed movie types
```
Tests run: 5, Failures: 0, Errors: 0, Skipped: 0
```

### Pricing

| Item | Price |
|------|-------|
| Back to the Future DVD | 15€ |
| Other DVD | 20€ |

### Discounts (BTTF only)

| Condition | Discount | Calculation |
|-----------|----------|-------------|
| 2 different BTTF movies | 10% off | Total × 0.9 |
| 3 different BTTF movies | 20% off | Total × 0.8 |

**Important Notes:**
- Discounts apply **only** to "Back to the Future" DVDs
- Discount is based on the number of **different** episodes, not total quantity
- Other movies are not discounted

## 📚 Examples

### Example 1: Three Different BTTF Movies

**Input:**
```
Back to the Future 1
Back to the Future 2
Back to the Future 3
```

**Calculation:**
- 3 different BTTF movies → 20% discount
- (3 × 15€) × 0.8 = **36€**

---

### Example 2: Two Different BTTF Movies

**Input:**
```
Back to the Future 1
Back to the Future 3
```

**Calculation:**
- 2 different BTTF movies → 10% discount
- (2 × 15€) × 0.9 = **27€**

---

### Example 3: Single BTTF Movie

**Input:**
```
Back to the Future 1
```

**Calculation:**
- 1 BTTF movie → No discount
- 1 × 15€ = **15€**

---

### Example 4: Multiple DVDs with Duplicates

**Input:**
```
Back to the Future 1
Back to the Future 2
Back to the Future 3
Back to the Future 2
```

**Calculation:**
- 4 DVDs total, but only 3 different → 20% discount
- (4 × 15€) × 0.8 = **48€**

---

### Example 5: BTTF + Other Movies

**Input:**
```
Back to the Future 1
Back to the Future 2
Back to the Future 3
La chèvre
```

**Calculation:**
- BTTF: (3 × 15€) × 0.8 = 36€
- Other: 1 × 20€ = 20€
- **Total: 56€**

## 🏗️ Design Decisions

### Architecture

The project follows clean architecture principles with clear separation of concerns:

- **Model Layer**: Represents domain concepts (MovieType enum)
- **Service Layer**: Contains business logic (calculations, discounts)
- **Separation of Concerns**: Each class has a single, well-defined responsibility

### Design Patterns

#### Strategy Pattern
- `DiscountCalculator` encapsulates discount calculation logic
- Easy to extend with new discount rules without modifying existing code

#### Dependency Injection
- Constructor-based injection for testability
- Allows easy mocking in unit tests

### Code Quality

- **SOLID Principles**: Single Responsibility, Open/Closed, etc.
- **Clean Code**: Descriptive naming, small methods, clear logic
- **Defensive Programming**: Null checks, input validation, edge case handling
- **Comprehensive Testing**: Unit tests for all scenarios

### Key Features

#### Case-Insensitive Matching
```java
// All these variations work:
"Back to the Future 1"
        "back to the future 1"
        "BACK TO THE FUTURE 1"
        "  Back to the Future 1  "
```

#### Robust Input Handling
- Handles `null` input gracefully (returns 0.0)
- Trims whitespace from movie names
- Parses multi-line input correctly

## 🧑‍💻 Development

### Code Style

The project follows standard Java conventions:
- Clear and descriptive variable names
- Comprehensive JavaDoc comments
- Consistent formatting (Google Java Style Guide)

### Future Enhancements

Possible improvements for production use:
- Input validation with custom exceptions
- Support for different currencies
- Configuration file for prices and discounts
- REST API endpoint
- Database persistence

## 📝 License

This project is part of a technical assessment for Ekinox.

## 👤 Author

**BEN CHAFAI AYOUB**
- GitHub: [@aben-cha](https://github.com/aben-cha)
- LinkedIn: [ayoub-ben-chafai](https://www.linkedin.com/in/ayoub-ben-chafai-95717a204/)