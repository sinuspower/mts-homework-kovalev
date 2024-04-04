package ru.mtsb.okovalev.lessonthree;

import ru.mtsb.okovalev.lessonthree.animals.*;
import ru.mtsb.okovalev.lessonthree.animals.enums.*;
import ru.mtsb.okovalev.lessonthree.util.RandomEnumValue;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Random;

/**
 * Фабрика для создания животных.
 */
public class AnimalsFactory {
    /**
     * Максимальный возраст животного в днях.
     * Используется для генерации псевдослучайной даты рождения.
     */
    public static final int MAX_ANIMAL_AGE_DAYS = 10000;

    /**
     * Максимальная стоимость животного в USD.
     * Используется для генерации псевдослучайной стоимости.
     */
    public static final int MAX_ANIMAL_COST_USD = 10000;

    /**
     * Возвращает псевдослучайное животное псевдослучайного вида.
     *
     * @return псевдослучайное животное
     */
    public Animal getRandomAnimal() {
        return getRandomAnimal(AnimalType.getRandom());
    }

    /**
     * Возвращает псевдослучайное животное указанного вида.
     *
     * @param type Тип животного, которое необходимо создать.
     * @return псевдослучайное животное
     */
    public Animal getRandomAnimal(AnimalType type) {
        if (Objects.isNull(type)) {
            return null;
        }

        Animal randomAnimal = null;
        Random random = new Random();

        String breed;
        String character = new RandomEnumValue<>(AnimalCharacter.class).getString();
        String name = new RandomEnumValue<>(AnimalName.class).getString();
        LocalDate birthdate = LocalDate.now().minusDays(random.nextInt(MAX_ANIMAL_AGE_DAYS));
        double cost;

        switch (type) {
            case WOLF:
                breed = new RandomEnumValue<>(WolfBreed.class).getString();
                randomAnimal = new Wolf(breed, character, name, birthdate);
                break;
            case SHARK:
                breed = new RandomEnumValue<>(SharkBreed.class).getString();
                randomAnimal = new Shark(breed, character, name, birthdate);
                break;
            case DOG:
                breed = new RandomEnumValue<>(DogBreed.class).getString();
                cost = random.nextDouble() * random.nextInt(MAX_ANIMAL_COST_USD);
                randomAnimal = new Dog(breed, character, name, birthdate, cost);
                break;
            case CAT:
                breed = new RandomEnumValue<>(CatBreed.class).getString();
                cost = random.nextDouble() * random.nextInt(MAX_ANIMAL_COST_USD);
                randomAnimal = new Cat(breed, character, name, birthdate, cost);
                break;
        }

        return randomAnimal;
    }
}
