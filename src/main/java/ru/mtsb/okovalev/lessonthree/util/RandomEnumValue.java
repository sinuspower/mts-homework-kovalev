package ru.mtsb.okovalev.lessonthree.util;

import java.util.Random;

/**
 * Предоставляет инструменты для получения псейдослучайных значений перечислений.
 *
 * @param <T> наследник класса Enum, псевдослучайное значение которого необходимо получать
 */
public class RandomEnumValue<T extends Enum<T>> {
    private static final Random random = new Random();
    private final T[] values;

    /**
     * Создаёт объект RandomEnumValue, инициализирует массив всех
     * возможных значений переданного на вход перечисления.
     *
     * @param e Класс перечисления (Enum.getClass())
     */
    public RandomEnumValue(Class<T> e) {
        values = e.getEnumConstants();
    }

    /**
     * Возвращает псевдослучайное значение базового перечисления из массива
     * всех возможных значений, инициализированного конструктором RandomEnumValue.
     *
     * @return псевдослучайное значение перечисления
     */
    public T get() {
        return values[random.nextInt(values.length)];
    }

    /**
     * Возвращает строковое представление псевдослучайного значения перечисления из массива
     * всех возможных значений, инициализированного конструктором RandomEnumValue.
     * Использует метод toString() базового перечисления.
     *
     * @return строковое представление псевдослучайного значения перечисления
     */
    public String getString() {
        return values[random.nextInt(values.length)].toString();
    }
}
