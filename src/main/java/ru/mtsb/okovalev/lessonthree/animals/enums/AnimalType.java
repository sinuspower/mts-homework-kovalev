package ru.mtsb.okovalev.lessonthree.animals.enums;

import java.util.Random;

public enum AnimalType {
    WOLF, SHARK, DOG, CAT;

    private static final Random random = new Random();
    private static final AnimalType[] animalTypes = values();

    public static AnimalType getRandom() {
        return animalTypes[random.nextInt(animalTypes.length)];
    }
}
