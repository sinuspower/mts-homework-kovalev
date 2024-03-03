package ru.mtsb.okovalev.lessonthree.animals;

import ru.mtsb.okovalev.lessonthree.animals.enums.AnimalCharacter;
import ru.mtsb.okovalev.lessonthree.animals.enums.WolfBreed;
import ru.mtsb.okovalev.lessonthree.util.RandomEnumValue;

public class Wolf extends Predator {
    public Wolf() {
        super(new RandomEnumValue<>(WolfBreed.class).getString(), new RandomEnumValue<>(AnimalCharacter.class).getString());
    }

    @SuppressWarnings("unused")
    public Wolf(String breed, String character) {
        super(breed, character);
    }

    @Override
    public String toString() {
        return "{\"Wolf\": {" + "\"breed\": \"" + getBreed() + "\", \"character\": \"" + getCharacter() + "\"}}";
    }

    @Override
    public void move() {
        System.out.println("The wolf is running.");
    }

    @Override
    public void eat() {
        System.out.println("The wolf is hunting large hoofed mammals.");
    }

    @Override
    public void sound() {
        System.out.println("The wolf is howling.");
    }

    @Override
    public void live() {
        move();
        eat();
        sound();
    }
}
