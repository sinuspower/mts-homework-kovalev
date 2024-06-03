package ru.mtsb.okovalev.lessoneight;

import java.math.BigInteger;
import java.util.concurrent.ExecutionException;

public class Main {
    @SuppressWarnings("unused")
    public static void main(String[] args) {
        try {
            int n = 1000000;
            System.out.println("\tSorting.\nArray length = " + n);
            int[] array = Util.generateRandomArray(n, -100, 100); // throws
            int[] arrayCopy = array.clone();
            long startNanoseconds = System.nanoTime();
            Util.sort(array);
            long timeSpentNanoseconds = System.nanoTime() - startNanoseconds;
            System.out.println("Single time spent: " + timeSpentNanosecondsToString(timeSpentNanoseconds));

            startNanoseconds = System.nanoTime();
            Util.parallelSort(arrayCopy); // throws
            timeSpentNanoseconds = System.nanoTime() - startNanoseconds;
            System.out.println("Parallel time spent: " + timeSpentNanosecondsToString(timeSpentNanoseconds));

            /* ------------------------------------------------------------------------ */

            n = 100000;
            System.out.println("\n\tFactorial.\nN = " + n);
            startNanoseconds = System.nanoTime();
            BigInteger factorial = Util.factorial(n); // throws
            timeSpentNanoseconds = System.nanoTime() - startNanoseconds;
            System.out.println("Single time spent: " + timeSpentNanosecondsToString(timeSpentNanoseconds));

            startNanoseconds = System.nanoTime();
            Util.parallelFactorial(n); // throws
            timeSpentNanoseconds = System.nanoTime() - startNanoseconds;
            System.out.println("Parallel time spent: " + timeSpentNanosecondsToString(timeSpentNanoseconds));

            /* ------------------------------------------------------------------------ */

            n = 20000;
            System.out.println("\n\tFibonacci sequence.\nN = " + n);
            startNanoseconds = System.nanoTime();
            BigInteger[] fibonacci = Util.fibonacci(n);
            timeSpentNanoseconds = System.nanoTime() - startNanoseconds;
            System.out.println("Single time spent: " + timeSpentNanosecondsToString(timeSpentNanoseconds));

            startNanoseconds = System.nanoTime();
            BigInteger[] parallelFibonacci = Util.parallelFibonacci(n); // throws
            timeSpentNanoseconds = System.nanoTime() - startNanoseconds;
            System.out.println("Parallel time spent: " + timeSpentNanosecondsToString(timeSpentNanoseconds));
        } catch (IllegalArgumentException | InterruptedException | ExecutionException e) {
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
