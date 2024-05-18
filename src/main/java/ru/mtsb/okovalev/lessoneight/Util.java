package ru.mtsb.okovalev.lessoneight;

import java.util.Objects;
import java.util.Random;

/**
 * Класс содержит набор статических методов, необходимых для решения задач в рамках задания.
 */
public class Util {
    private static final Random RANDOM = new Random();

    /**
     * Генерирует массив псевдослучайных чисел.
     * Для генерации массива одинаковых элементов указывается min = max.
     *
     * @param length Длина результирующего массива
     * @param min    Минимальное значение каждого элемента массива
     * @param max    Максимальное значение каждого элемента массива
     * @return Массив псевдослучайных целых чисел
     * @throws IllegalArgumentException если указана длина массива &lt; 1 или min &gt; max
     */
    public static int[] generateRandomArray(int length, int min, int max) throws IllegalArgumentException {
        if (length < 1) throw new IllegalArgumentException("Length < 1 (" +
                length + ") has been specified");
        if (min > max) throw new IllegalArgumentException("Specified min is greater than max(" +
                min + " > " + max + ")");

        int[] result = new int[length];
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            result[i] = random.nextInt(max - min + 1) + min;
        }

        return result;
    }

    /**
     * Выполняет сортировку массива в порядке возрастания элементов посредством
     * базовой реализации алгоритма быстрой сортировки.
     *
     * @param array Массив целых чисел
     */
    public static void sort(int[] array) {
        if (Objects.isNull(array)) {
            return;
        }

        quicksort(array, 0, array.length - 1);
    }

    /**
     * Вычисляет факториал числа n.
     *
     * @param n Число [0, 20] – факториал 21 и далее больше максимального
     *          значения типа Long = 9223372036854775807
     * @return Факториал
     * @throws IllegalArgumentException если n > 20 или n < 0
     */
    public static long factorial(long n) throws IllegalArgumentException {
        if (n < 0 || n > 20) {
            throw new IllegalArgumentException("Expected n in [0, 20]. Actual n = " + n);
        }

        if (n == 0) return 1;

        long factorial = 1;

        for (int i = 2; i <= n; i++) {
            factorial *= i;
        }

        return factorial;
    }

    /**
     * Базовая реализация алгоритма быстрой сортировки. Изменяет исходный массив.
     * Опорный элемент выбирается псевдослучайным образом.
     *
     * @param array      Исходный массив, результирующий массив
     * @param startIndex Начало среза массива для сортировки
     * @param endIndex   Конец среза массива для сортировки
     */
    private static void quicksort(int[] array, int startIndex, int endIndex) {
        if (endIndex - startIndex + 1 < 2) return; // slice of less than two elements

        int pivot = partition(array,
                RANDOM.nextInt(endIndex - startIndex + 1) + startIndex,
                startIndex, endIndex
        );

        quicksort(array, startIndex, pivot == startIndex ? pivot : pivot - 1);
        quicksort(array, pivot == endIndex ? pivot : pivot + 1, endIndex);
    }

    /**
     * Осуществляет перераспределение элементов массива относительно заранее выбранного
     * опорного элемента: всё, что меньше этого элемента, располагается слева от него,
     * больше либо равно – справа.
     * Возвращает индекс опорного элемента после перераспределения. Изменяет исходный массив.
     *
     * @param array             Исходный массив, результирующий массив
     * @param pivotIndexInitial Заранее выбранный индекс опорного элемента
     * @param startIndex        Начало среза массива для перераспределения
     * @param endIndex          Конец среза массива для перераспределения
     * @return Индекс опорного элемента после распределения
     */
    private static int partition(int[] array, int pivotIndexInitial, int startIndex, int endIndex) {
        int pivotIndex = pivotIndexInitial;

        // go to the left from pivot and move big elements to the right of pivot
        int i = pivotIndex;
        while (i >= startIndex) {
            if (array[i] > array[pivotIndex]) {
                if (pivotIndex != startIndex && i != pivotIndex - 1) {
                    // swap big element with the nearest left element from pivot
                    array[i] += array[pivotIndex - 1];
                    array[pivotIndex - 1] = array[i] - array[pivotIndex - 1];
                    array[i] -= array[pivotIndex - 1];
                }

                // swap pivot with the nearest left element from it
                array[pivotIndex] += array[pivotIndex - 1];
                array[pivotIndex - 1] = array[pivotIndex] - array[pivotIndex - 1];
                array[pivotIndex] -= array[pivotIndex - 1];
                pivotIndex--;
            }
            i--;
        }

        // go to the right from pivot and move small elements to the left of pivot
        i = pivotIndex;
        while (i <= endIndex) {
            if (i != pivotIndex && array[i] <= array[pivotIndex]) {
                if (pivotIndex != endIndex && i != pivotIndex + 1) {
                    // swap small element with the nearest right element from pivot
                    array[i] += array[pivotIndex + 1];
                    array[pivotIndex + 1] = array[i] - array[pivotIndex + 1];
                    array[i] -= array[pivotIndex + 1];
                }

                // swap pivot with the nearest right element from it
                array[pivotIndex] += array[pivotIndex + 1];
                array[pivotIndex + 1] = array[pivotIndex] - array[pivotIndex + 1];
                array[pivotIndex] -= array[pivotIndex + 1];
                pivotIndex++;
            }
            i++;
        }

        return pivotIndex;
    }
}
