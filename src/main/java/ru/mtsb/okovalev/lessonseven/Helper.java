package ru.mtsb.okovalev.lessonseven;

import ru.mtsb.okovalev.lessonthree.CreateAnimalsServiceImpl;
import ru.mtsb.okovalev.lessonthree.animals.Animal;
import ru.mtsb.okovalev.lessonthree.animals.Dog;
import ru.mtsb.okovalev.lessonthree.animals.enums.AnimalCharacter;
import ru.mtsb.okovalev.lessonthree.animals.enums.AnimalName;
import ru.mtsb.okovalev.lessonthree.animals.enums.DogBreed;

import java.time.LocalDate;
import java.util.ArrayList;

public class Helper {
    public static ArrayList<Animal> getAnimalsListWithDuplicates() {
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
