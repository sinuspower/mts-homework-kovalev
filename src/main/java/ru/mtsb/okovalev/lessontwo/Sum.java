package ru.mtsb.okovalev.lessontwo;

/**
 * Простая программа вместо "Hello World". Записывает в стандартный поток вывода
 * сумму целых чисел, введённых в качестве параметров командной строки.
 */
public class Sum {
    public static void main(String[] args) {
        int n = args.length;
        int[] numbers = new int[n];

        try {
            for (int i = 0; i < n; i++) {
                numbers[i] = Integer.parseInt(args[i]);
            }
        } catch (NumberFormatException e) {
            System.out
                    .println("NumberFormatException\n" + e.getMessage() + "\nAll of arguments must be integer numbers");
            return;
        }

        System.out.println(sum(numbers));
    }

    public static int sum(int... numbers) {
        int sum = 0;
        for (int number : numbers) {
            sum += number;
        }

        return sum;
    }
}