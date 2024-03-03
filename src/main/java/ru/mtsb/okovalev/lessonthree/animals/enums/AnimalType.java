package ru.mtsb.okovalev.lessonthree.animals.enums;

import java.util.Random;

/**
 * Перечисление видов животных. Содержит значения, соответствующие всем "листовым"
 * классам, определённым в пакете "ru.mtsb.okovalev.lessonthree.animals".
 */
public enum AnimalType {
    WOLF, SHARK, DOG, CAT;

    private static final Random random = new Random();
    private static final AnimalType[] animalTypes = values();

    /**
     * Возвращает псевдослучайный вид животного из перечисления AnimalType.
     *
     * @return псевдослучайное значение AnimalType
     */
    public static AnimalType getRandom() {
        return animalTypes[random.nextInt(animalTypes.length)];
    }
}
