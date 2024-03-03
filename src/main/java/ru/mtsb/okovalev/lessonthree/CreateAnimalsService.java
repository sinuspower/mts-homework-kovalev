package ru.mtsb.okovalev.lessonthree;

import ru.mtsb.okovalev.lessonthree.animals.*;

import java.util.ArrayList;
import java.util.Random;

public interface CreateAnimalsService {
    int DEFAULT_ANIMALS_COUNT = 10;

    enum AnimalType {
        WOLF, SHARK, DOG, CAT;

        private static final Random random = new Random();
        private static final AnimalType[] animalTypes = values();

        public static AnimalType getRandom() {
            return animalTypes[random.nextInt(animalTypes.length)];
        }
    }

    default ArrayList<Animal> create() {
        ArrayList<Animal> animals = new ArrayList<>();

        int i = 0;
        while (i < DEFAULT_ANIMALS_COUNT) {
            animals.add(randomAnimal());
            i++;
        }

        System.out.println(DEFAULT_ANIMALS_COUNT + " animals created by CreateAnimalsService.create()");
        return animals;
    }

    default Animal randomAnimal() {
        Animal randomAnimal;

        switch (AnimalType.getRandom()) {
            case WOLF:
                randomAnimal = new Wolf();
                break;
            case SHARK:
                randomAnimal = new Shark();
                break;
            case DOG:
                randomAnimal = new Dog();
                break;
            case CAT:
                randomAnimal = new Cat();
                break;
            default:
                randomAnimal = new Wolf("Dingo", "nervous");
                break;
        }

        return randomAnimal;
    }
}
