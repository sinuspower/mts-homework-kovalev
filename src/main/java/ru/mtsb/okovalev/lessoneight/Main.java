package ru.mtsb.okovalev.lessoneight;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println("\tSorting.");
        try {
            int[] array = Util.generateRandomArray(1000, -100, 100); // throws
            System.out.println("Source array (" + array.length + "): " + Arrays.toString(array));

            long startNanoseconds = System.nanoTime();
            Util.sort(array);
            long timeSpentNanoseconds = System.nanoTime() - startNanoseconds;

            System.out.println("Sorted: " + Arrays.toString(array));
            System.out.println("Time spent: " + timeSpentNanosecondsToString(timeSpentNanoseconds));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        try {
            System.out.println("\n\tFactorial.");

            long n = 18;
            long startNanoseconds = System.nanoTime();
            long factorial = Util.factorial(n); // throws
            long timeSpentNanoseconds = System.nanoTime() - startNanoseconds;

            System.out.println("factorial(" + n + ") = " + factorial);
            System.out.println("Time spent: " + timeSpentNanosecondsToString(timeSpentNanoseconds));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static String timeSpentNanosecondsToString(long timeSpentNanoseconds) {
        String formatted;

        if (timeSpentNanoseconds < 1000) {
            formatted = timeSpentNanoseconds + "ns";
        } else if (timeSpentNanoseconds < 1000000) {
            formatted = timeSpentNanoseconds / 1000 + "us";
        } else {
            formatted = timeSpentNanoseconds / 1000000 + "ms";
        }

        return formatted;
    }
}
