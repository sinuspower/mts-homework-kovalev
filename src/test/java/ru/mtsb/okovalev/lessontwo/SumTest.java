package ru.mtsb.okovalev.lessontwo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.mtsb.okovalev.lessontwo.Sum.sum;

class SumTest {
    @Test
    @DisplayName("Sum without parameters")
    void sumWithoutParameters() {
        assertEquals(0, sum());
    }

    @Test
    @DisplayName("Sum with one parameter")
    void sumWithOneParameter() {
        assertEquals(300, sum(300));
    }

    @Test
    @DisplayName("Sum with any count of parameters")
    void sumWithAnyParameters() {
        assertEquals(6, sum(1, 2, 3));
    }

    @Test
    @DisplayName("Sum with array of parameters")
    void sumWithArrayOfParameters() {
        int[] params = new int[]{15, 20};
        assertEquals(35, sum(params));
    }
}