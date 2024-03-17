package ru.mtsb.okovalev.lessonthree.animals.enums;

import java.util.Random;

/**
 * Перечисление видов животных. Содержит значения, соответствующие всем "листовым"
 * классам, определённым в пакете "ru.mtsb.okovalev.lessonthree.animals".
 */
public enum AnimalType {
    WOLF("Wolf"), SHARK("Shark"), DOG("Dog"), CAT("Cat");

    private final String printable;

    AnimalType(String printable) {
        this.printable = printable;
    }

    private static final Random random = new Random();
    private static final AnimalType[] animalTypes = values();

    /**
     * Возвращает представление типа животного, применимое для человекочитаемого вывода.
     *
     * @return тип животного в формате для человекочитаемого вывода
     */
    @Override
    public String toString() {
        return this.printable;
    }

    /**
     * Возвращает псевдослучайный вид животного из перечисления AnimalType.
     *
     * @return псевдослучайное значение AnimalType
     */
    public static AnimalType getRandom() {
        return animalTypes[random.nextInt(animalTypes.length)];
    }
}
