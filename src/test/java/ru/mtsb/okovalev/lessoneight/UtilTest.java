package ru.mtsb.okovalev.lessoneight;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

public class UtilTest {
    @Test
    @DisplayName("Util.generateRandomArray - int - length is less than 1")
    void generateRandomArray_int_lengthIsLessThanOne() {
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> Util.generateRandomArray(0, 10, 100)
        );

        assertEquals("Length < 1 (0) has been specified", thrown.getMessage());
    }

    @Test
    @DisplayName("Util.generateRandomArray - int - min is greater than max")
    void generateRandomArray_int_minIsGreaterThanMax() {
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> Util.generateRandomArray(10, 100, 10)
        );

        assertEquals("Specified min is greater than max(100 > 10)", thrown.getMessage());
    }

    @ParameterizedTest(name = "Length = {0}")
    @ValueSource(ints = {12, 10, 108, 1000})
    @DisplayName("Util.generateRandomArray - int - generate positive numbers")
    void generateRandomArray_int_generatePositiveNumbers(int length) {
        assertDoesNotThrow(() -> Util.generateRandomArray(length, 10, 100));

        int[] array = Util.generateRandomArray(length, 1, 100);
        assertEquals(length, array.length);

        boolean elementsArePositive = true;
        for (int a : array) {
            if (a < 0) {
                elementsArePositive = false;
                break;
            }
        }

        assertTrue(elementsArePositive);
    }

    @ParameterizedTest(name = "Length = {0}")
    @ValueSource(ints = {12, 10, 108, 1000})
    @DisplayName("Util.generateRandomArray - int - generate equal numbers")
    void generateRandomArray_int_generateEqualNumbers(int length) {
        int number = 156;

        assertDoesNotThrow(() -> Util.generateRandomArray(length, number, number));

        int[] array = Util.generateRandomArray(length, number, number);
        assertEquals(length, array.length);

        boolean elementsAreEqual = true;
        for (int a : array) {
            if (a != number) {
                elementsAreEqual = false;
                break;
            }
        }

        assertTrue(elementsAreEqual);
    }

    @ParameterizedTest(name = "Length = {0}")
    @ValueSource(ints = {12, 10, 108, 1000})
    @DisplayName("Util.generateRandomArray - int - generate negative numbers")
    void generateRandomArray_int_generateNegativeNumbers(int length) {
        assertDoesNotThrow(() -> Util.generateRandomArray(length, -100, -1));

        int[] array = Util.generateRandomArray(length, -100, -1);
        assertEquals(length, array.length);

        boolean elementsAreNegative = true;
        for (int a : array) {
            if (a > 0) {
                elementsAreNegative = false;
                break;
            }
        }

        assertTrue(elementsAreNegative);
    }

    @Test
    @DisplayName("Util.generateRandomArray - int - elements are not less than min")
    void generateRandomArray_int_elementsAreNotLessThanMin() {
        boolean lessThanMin = false;
        int repetitionsCount = 10, arrayLength = 1000, min = 10, max = 100;

        for (int i = 0; i < repetitionsCount; i++) {
            int[] array = Util.generateRandomArray(arrayLength, min, max);
            for (int a : array) {
                if (a < min) {
                    lessThanMin = true;
                    break;
                }
            }

            if (lessThanMin) break;
        }

        assertFalse(lessThanMin);
    }

    @Test
    @DisplayName("Util.generateRandomArray - int - elements are not greater than max")
    void generateRandomArray_int_elementsAreNotGreaterThanMax() {
        boolean greaterThanMax = false;
        int repetitionsCount = 10, arrayLength = 1000, min = 10, max = 100;

        for (int i = 0; i < repetitionsCount; i++) {
            int[] array = Util.generateRandomArray(arrayLength, min, max);
            for (int a : array) {
                if (a > max) {
                    greaterThanMax = true;
                    break;
                }
            }

            if (greaterThanMax) break;
        }

        assertFalse(greaterThanMax);
    }

    @ParameterizedTest(name = "array is {0}")
    @NullSource
    @DisplayName("Util.sort - int - array is null")
    void sort_int_arrayIsNull(int[] array) {
        assertDoesNotThrow(() -> Util.sort(array));
        assertTrue(Objects.isNull(array));
    }

    @Test
    @DisplayName("Util.sort - int - array is empty")
    void sort_int_arrayIsEmpty() {
        int[] array = new int[]{};
        assertDoesNotThrow(() -> Util.sort(array));
        assertEquals(Arrays.toString(array), Arrays.toString(new int[]{}));
    }

    @Test
    @DisplayName("Util.sort - int - array of one element")
    void sort_int_arrayOfOneElement() {
        int[] array = new int[]{16};
        assertDoesNotThrow(() -> Util.sort(array));
        assertEquals(Arrays.toString(array), Arrays.toString(new int[]{16}));
    }

    @Test
    @DisplayName("Util.sort - int - array of two elements")
    void sort_int_arrayOfTwoElements() {
        int[] array = new int[]{16, 1};
        assertDoesNotThrow(() -> Util.sort(array));
        assertEquals(Arrays.toString(array), Arrays.toString(new int[]{1, 16}));
    }

    @Test
    @DisplayName("Util.sort - int - asc sorted array")
    void sort_int_ascSortedArray() {
        int[] array = new int[]{16, 18, 47, 89, 130};
        assertDoesNotThrow(() -> Util.sort(array));
        assertEquals(Arrays.toString(array), Arrays.toString(new int[]{16, 18, 47, 89, 130}));
    }

    @Test
    @DisplayName("Util.sort - int - desc sorted array")
    void sort_int_descSortedArray() {
        int[] array = new int[]{140, 120, 89, 50, -6};
        assertDoesNotThrow(() -> Util.sort(array));
        assertEquals(Arrays.toString(array), Arrays.toString(new int[]{-6, 50, 89, 120, 140}));
    }

    @Test
    @DisplayName("Util.sort - int - is properly sorted")
    void sort_int_isProperlySorted() {
        boolean properlySorted = true;
        int repetitionsCount = 10, arrayLength = 10000, min = -100, max = 100;

        for (int i = 0; i < repetitionsCount; i++) {
            int[] array = Util.generateRandomArray(arrayLength, min, max);
            Util.sort(array);

            for (int j = 0; j < arrayLength - 1; j++) {
                if (array[j] > array[j + 1]) {
                    properlySorted = false;
                    break;
                }
            }

            if (!properlySorted) break;
        }

        assertTrue(properlySorted);
    }

    @Test
    @DisplayName("Util.factorial - N is less than 0")
    void factorial_nIsLessThanZero() {
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> Util.factorial(-13)
        );

        assertEquals("Expected n in [0, 20]. Actual n = -13", thrown.getMessage());
    }

    @Test
    @DisplayName("Util.factorial - N is greater than 20")
    void factorial_nIsGreaterThanTwenty() {
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> Util.factorial(40)
        );

        assertEquals("Expected n in [0, 20]. Actual n = 40", thrown.getMessage());
    }

    @Test
    @DisplayName("Util.factorial - N = 0")
    void factorial_nEqualsZero() {
        assertDoesNotThrow(() -> Util.factorial(0));
        assertEquals(1, Util.factorial(0));
    }

    @Test
    @DisplayName("Util.factorial - N = 10")
    void factorial_nEqualsTen() {
        assertDoesNotThrow(() -> Util.factorial(10));
        assertEquals(3628800, Util.factorial(10));
    }
}
