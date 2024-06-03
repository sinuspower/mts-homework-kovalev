package ru.mtsb.okovalev.lessoneight;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.*;

/**
 * Класс содержит набор статических методов, необходимых для решения задач в рамках задания.
 */
public class Util {
    private static final Random RANDOM = new Random();
    private static final int THREADS_COUNT = 8;
    private static final int MIN_DATA_LENGTH = 3;
    private static final int MAX_WAIT_MINUTES = 5;

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
     * базовой реализации алгоритма быстрой сортировки. Изменяет содержимое исходного массива.
     *
     * @param array Массив целых чисел
     */
    public static void sort(int[] array) {
        if (Objects.isNull(array)) return;

        quicksort(array, 0, array.length - 1);
    }

    /**
     * Выполняет сортировку массива в порядке возрастания элементов.
     * Сортировка осуществляется в THREADS_COUNT параллельных потоках.
     * Минимальное количество элементов массива для каждого
     * потока исполнения = MIN_DATA_LENGTH. Изменяет исходный массив.
     *
     * @param array Массив целых чисел
     * @throws InterruptedException если произошло исключение во время ожидания
     *                              завершения всех потоков исполнения
     */
    @SuppressWarnings("ResultOfMethodCallIgnored") // executor.awaitTermination
    public static void parallelSort(int[] array) throws InterruptedException {
        if (Objects.isNull(array)) return;
        if (array.length / THREADS_COUNT < MIN_DATA_LENGTH) sort(array); // sort in non-parallel

        // start execution threads, do sorting
        ExecutorService executor = Executors.newFixedThreadPool(THREADS_COUNT);

        int sliceSize = array.length / THREADS_COUNT;
        if (array.length % THREADS_COUNT != 0) sliceSize++;

        for (int i = 0; i < THREADS_COUNT; i++) {
            int startIndex = i * sliceSize;
            int endIndex = Math.min(startIndex + sliceSize - 1, array.length - 1);

            executor.execute(() -> quicksort(array, startIndex, endIndex));
        }

        // wait for all execution threads will be done
        executor.shutdown();
        executor.awaitTermination(MAX_WAIT_MINUTES, TimeUnit.MINUTES);

        // start collecting results
        int[] tmp = new int[array.length]; // for merging of sorted sub-arrays
        int[] indexes = new int[THREADS_COUNT]; // array of current pointers in sub-arrays

        // init indexes in sorted sub-arrays
        for (int i = 0; i < indexes.length; i++) {
            indexes[i] = Math.min(i * sliceSize, array.length - 1);
        }

        int min, indexForInc;
        for (int i = 0; i < tmp.length; i++) {
            min = Integer.MAX_VALUE;
            indexForInc = 0; // index of sub-array from which the minimum will be taken
            for (int j = 0; j < indexes.length; j++) { // take minimum among the first elements of sub-arrays
                if (indexes[j] != -1 && array[indexes[j]] < min) {
                    min = array[indexes[j]];
                    indexForInc = j;
                }
            }

            tmp[i] = min;
            // increase the index in sub-array if it isn't out of sub-array's bounds
            if (indexes[indexForInc] < array.length - 1 && indexes[indexForInc] < indexForInc * sliceSize + sliceSize - 1) {
                indexes[indexForInc]++;
            } else { // out of bounds, all elements of this sub-array are in result
                indexes[indexForInc] = -1; // do not use this index
            }
        }

        // write fully sorted array to source
        System.arraycopy(tmp, 0, array, 0, array.length);
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

    /**
     * Вычисляет факториал числа n.
     *
     * @param n Число
     * @return Факториал
     * @throws IllegalArgumentException если указан параметр n &lt; 0
     */
    public static BigInteger factorial(int n) throws IllegalArgumentException {
        if (n < 0) throw new IllegalArgumentException("Factorial of " + n + " is not defined");

        BigInteger factorial = new BigInteger("1");
        if (n == 0) return factorial;

        return subfactorial(2, n);
    }

    /**
     * Вычисляет факториал числа n. Для вычисления использует THREADS_COUNT параллельных потоков.
     * Минимальная длина подпоследовательности натуральных чисел для каждого
     * потока исполнения = MIN_DATA_LENGTH.
     *
     * @param n Число
     * @return Факториал
     * @throws IllegalArgumentException если указан параметр n &lt; 0
     * @throws InterruptedException     если произошло исключение во время ожидания
     *                                  завершения всех потоков исполнения
     * @throws ExecutionException       если возникло исключение во время выполнения параллельной задачи (проброс исключения из Future.get())
     */
    @SuppressWarnings({"ResultOfMethodCallIgnored", "UnusedReturnValue"})
    public static BigInteger parallelFactorial(int n) throws IllegalArgumentException, InterruptedException, ExecutionException {
        if (n / THREADS_COUNT < MIN_DATA_LENGTH) return factorial(n); // get in non-parallel

        // start execution threads, do calculation
        ExecutorService executor = Executors.newFixedThreadPool(THREADS_COUNT);

        BigInteger factorial = new BigInteger("1");
        int step = n / THREADS_COUNT;

        List<Future<BigInteger>> futures = new ArrayList<>(); // results will be here

        int lastEnd = -1;
        for (int i = 0; i < THREADS_COUNT; i++) {
            int start = lastEnd + 1;
            int end = Math.min(start + step, n);
            lastEnd = end;

            futures.add(
                    CompletableFuture.supplyAsync(
                            () -> Util.subfactorial(start, end), executor
                    )
            );
        }

        // wait for all execution threads will be done
        executor.shutdown();
        executor.awaitTermination(MAX_WAIT_MINUTES, TimeUnit.MINUTES);

        // get final result
        for (Future<BigInteger> future : futures) {
            factorial = factorial.multiply(future.get());
        }

        return factorial;
    }

    /**
     * Вычисляет произведение последовательности натуральных чисел.
     *
     * @param start Начало последовательности
     * @param end   Конец последовательности
     * @return Произведение натуральных чисел от start до end
     */
    private static BigInteger subfactorial(int start, int end) {
        if (start < 1) start = 1;
        BigInteger result = new BigInteger("1");

        for (int i = start; i <= end; i++) {
            result = result.multiply(new BigInteger(Integer.toString(i)));
        }

        return result;
    }

    /**
     * Возвращает последовательность чисел Фибоначчи до указанного индекса n.
     * Индексация начинается с нуля, поэтому длина результирующей последовательности = n + 1.
     *
     * @param n Индекс последнего элемента последовательности
     * @return Последовательность из n + 1 числа Фибоначчи
     * @throws IllegalArgumentException если указан параметр n &lt; 0
     */
    public static BigInteger[] fibonacci(int n) throws IllegalArgumentException {
        if (n < 0) throw new IllegalArgumentException("N < 0 (" + n + ") has been specified");

        int startIndex = 0;
        BigInteger[] result = new BigInteger[n + 1];
        fibonacciSubsequence(result, startIndex, n);

        return result;
    }

    /**
     * Возвращает последовательность чисел Фибоначчи до указанного индекса n.
     * Индексация начинается с нуля, поэтому длина результирующей последовательности = n + 1.
     * Для генерации последовательности использует THREADS_COUNT параллельных потоков.
     * Минимальная длина подпоследовательности для каждого потока исполнения = MIN_DATA_LENGTH.
     *
     * @param n Индекс последнего элемента последовательности
     * @return Последовательность из n + 1 числа Фибоначчи
     * @throws IllegalArgumentException если указан параметр n &lt; 0
     * @throws InterruptedException     если произошло исключение во время ожидания
     *                                  завершения всех потоков исполнения
     */
    @SuppressWarnings("ResultOfMethodCallIgnored") // executor.awaitTermination
    public static BigInteger[] parallelFibonacci(int n) throws IllegalArgumentException, InterruptedException {
        if (n / THREADS_COUNT < MIN_DATA_LENGTH) return fibonacci(n); // generate in non-parallel

        // start execution threads, do generation
        ExecutorService executor = Executors.newFixedThreadPool(THREADS_COUNT);

        int sliceSize = n / THREADS_COUNT;
        BigInteger[] result = new BigInteger[n + 1];

        int lastEndIndex = -1;
        for (int i = 0; i < THREADS_COUNT; i++) {
            int startIndex = lastEndIndex + 1;
            int endIndex = Math.min(startIndex + sliceSize, n);
            lastEndIndex = endIndex;

            executor.execute(() -> fibonacciSubsequence(result, startIndex, endIndex));
        }

        // wait for all execution threads will be done
        executor.shutdown();
        executor.awaitTermination(MAX_WAIT_MINUTES, TimeUnit.MINUTES);

        return result;
    }

    /**
     * Вычисляет подпоследовательность чисел Фибоначчи,
     * начиная с указанного индекса и заканчивая указанным индексом.
     *
     * @param sequence   Массив для записи последовательности Фибоначчи
     * @param startIndex Начальный индекс подпоследовательности
     * @param endIndex   Конечный индекс подпоследовательности
     */
    private static void fibonacciSubsequence(BigInteger[] sequence, int startIndex, int endIndex) {
        if (startIndex == endIndex) {
            sequence[startIndex] = fibonacciNumber(startIndex);
            return;
        }

        BigInteger prePrevious = new BigInteger("0");
        BigInteger previous = new BigInteger("1");

        if (startIndex < 2) {
            startIndex = 2;
            sequence[0] = new BigInteger("0");
            sequence[1] = new BigInteger("1");
        } else {
            prePrevious = fibonacciNumber(startIndex - 2);
            previous = fibonacciNumber(startIndex - 1);
        }

        for (int i = startIndex; i <= endIndex; i++) {
            sequence[i] = prePrevious.add(previous);
            prePrevious = new BigInteger(previous.toString());
            previous = new BigInteger(sequence[i].toString());
        }
    }

    /**
     * Вычисляет n-е число Фибоначчи на основе алгоритма быстрого перемножения квадратных матриц.
     *
     * @param n Индекс искомого числа Фибоначчи
     * @return N-е число Фибоначчи
     */
    private static BigInteger fibonacciNumber(int n) {
        if (n == 0) return new BigInteger("0");
        if (n < 3) return new BigInteger("1");

        BigInteger[][] q = new BigInteger[][]{
                {new BigInteger("1"), new BigInteger("1")},
                {new BigInteger("1"), new BigInteger("0")}
        };
        BigInteger[][] matrix = new BigInteger[][]{
                {new BigInteger("1"), new BigInteger("0")},
                {new BigInteger("0"), new BigInteger("1")}
        };

        int[] degreesOfTwo = degreesOfTwo(n);

        for (int degree : degreesOfTwo) {
            matrix = matrixMul(matrix, quickMatrixPow(q, degree));
        }

        return matrix[0][1]; // = Fₙ
    }

    /**
     * Возвращает массив чисел - степеней двойки, в сумму которых раскладывается заданное число.
     *
     * @param n Число
     * @return Массив степеней двойки для данного числа
     */
    private static int[] degreesOfTwo(int n) {
        String binaryString = Integer.toBinaryString(n);
        int binDigitsCount = binaryString.length();

        int onesCount = (int) binaryString.chars().filter(c -> c == '1').count();
        int[] result = new int[onesCount];

        int j = 0;
        for (int i = 0; i < binDigitsCount; i++) {
            int currentDegree = 1 << i;
            if ((n & currentDegree) > 0) {
                result[j++] = currentDegree;
            }
        }

        return result;
    }

    /**
     * Быстрое возведение квадратной матрицы в указанную степень.
     *
     * @param m Исходная матрица
     * @param p Степень, в которую должна быть возведена матрица
     * @return Матрица m в степени p
     */
    private static BigInteger[][] quickMatrixPow(BigInteger[][] m, int p) {
        if (p == 1) return m;

        BigInteger[][] halfPower = quickMatrixPow(m, p / 2);

        return matrixMul(halfPower, halfPower);
    }

    /**
     * Перемножает две матрицы 2*2.
     *
     * @param m1 Матрица 2*2
     * @param m2 Матрица 2*2
     * @return Произведение двух матриц 2*2
     */
    private static BigInteger[][] matrixMul(BigInteger[][] m1, BigInteger[][] m2) {
        return new BigInteger[][]{
                {
                        m1[0][0].multiply(m2[0][0]).add(m1[0][1].multiply(m2[1][0])),
                        m1[0][0].multiply(m2[0][1]).add(m1[0][1].multiply(m2[1][1]))
                },
                {
                        m1[1][0].multiply(m2[0][0]).add(m1[1][1].multiply(m2[1][0])),
                        m1[1][0].multiply(m2[0][1]).add(m1[1][1].multiply(m2[1][1]))
                }
        };
    }
}
