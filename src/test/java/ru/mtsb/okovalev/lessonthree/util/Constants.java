package ru.mtsb.okovalev.lessonthree.util;

import ru.mtsb.okovalev.lessonthree.animals.Cat;
import ru.mtsb.okovalev.lessonthree.animals.Dog;
import ru.mtsb.okovalev.lessonthree.animals.Shark;
import ru.mtsb.okovalev.lessonthree.animals.Wolf;
import ru.mtsb.okovalev.lessonthree.animals.enums.*;

import java.time.LocalDate;

public class Constants {
    public static final Dog DOG = new Dog(
            DogBreed.AFFENPINSCHER.toString(),
            AnimalCharacter.STRONG.toString(),
            AnimalName.CUDDLES.toString(),
            LocalDate.now(),
            300
    );

    public static final Cat CAT = new Cat(
            CatBreed.JAVANESE.toString(),
            AnimalCharacter.PHLEGMATIC.toString(),
            AnimalName.BAMBI.toString(),
            LocalDate.of(2011, 12, 26),
            1200
    );

    public static final Shark SHARK = new Shark(
            SharkBreed.BULL_SHARK.toString(),
            AnimalCharacter.SENTIMENTAL.toString(),
            AnimalName.DAISY.toString(),
            LocalDate.of(1991, 4, 20)
    );

    public static final Wolf WOLF = new Wolf(
            WolfBreed.MEXICAN_WOLF.toString(),
            AnimalCharacter.PHLEGMATIC.toString(),
            AnimalName.CESAR.toString(),
            LocalDate.now()
    );
}
