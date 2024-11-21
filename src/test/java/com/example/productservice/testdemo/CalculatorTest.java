package com.example.productservice.testdemo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CalculatorTest {
    /*
     * when -- then ---
     */
    @Test
    void whenAddTwoIntegersThenRightResultExpected() {
        // Arrange
        int a = 10;
        int b = 20;
        Calculator calculator = new Calculator();

        // Act
        int result = calculator.add(a, b);

        // Assert
        Assertions.assertEquals(40, result);
    }

    @Test
    void divide() {
        // Arrange
        int a = 10;
        int b = 0;
        Calculator c = new Calculator();

        // Act
//        int result = c.divide(a, b);

        // Assert
        Assertions.assertThrows(ArithmeticException.class, () -> c.divide(a, b));
    }
}