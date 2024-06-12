package ru.mtsb.okovalev.lessonseven;

import ru.mtsb.okovalev.lessonthree.CreateAnimalsServiceImpl;
import ru.mtsb.okovalev.lessonthree.animals.Animal;
import ru.mtsb.okovalev.lessonthree.animals.Dog;
import ru.mtsb.okovalev.lessonthree.animals.enums.AnimalCharacter;
import ru.mtsb.okovalev.lessonthree.animals.enums.AnimalName;
import ru.mtsb.okovalev.lessonthree.animals.enums.DogBreed;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Содержит вспомогательные методы, используемые в решениях нескольких заданий.
 */
public class Helper {
    /**
     * Возвращает список животных с дубликатами.
     *
     * @return список животных с дубликатами
     */
    public static ArrayList<Animal> getAnimalsListWithDuplicates() throws IOException {
        ArrayList<Animal> result = new CreateAnimalsServiceImpl().create(3);

        result.add(new Dog(
                DogBreed.BULL_TERRIER.toString(),
                AnimalCharacter.AMBITIOUS.toString(),
                AnimalName.TUTU.toString(),
                LocalDate.now(), 99.99)
        );

        result.add(new Dog(
                DogBreed.BULL_TERRIER.toString(),
                AnimalCharacter.AMBITIOUS.toString(),
                AnimalName.TUTU.toString(),
                LocalDate.now(), 99.99)
        );

        result.add(new Dog(
                DogBreed.AFFENPINSCHER.toString(),
                AnimalCharacter.PHLEGMATIC.toString(),
                AnimalName.CUDDLES.toString(),
                LocalDate.now(), 190)
        );

        result.add(new Dog(
                DogBreed.BULL_TERRIER.toString(),
                AnimalCharacter.AMBITIOUS.toString(),
                AnimalName.TUTU.toString(),
                LocalDate.now(), 99.99)
        );

        result.add(new Dog(
                DogBreed.AFFENPINSCHER.toString(),
                AnimalCharacter.PHLEGMATIC.toString(),
                AnimalName.CUDDLES.toString(),
                LocalDate.now(), 190)
        );

        return result;
    }
}
