package ru.mtsb.okovalev.lessonthree.animals;

import ru.mtsb.okovalev.lessonthree.animals.enums.AnimalCharacter;
import ru.mtsb.okovalev.lessonthree.animals.enums.SharkBreed;
import ru.mtsb.okovalev.lessonthree.util.RandomEnumValue;

public class Shark extends Predator {
    public Shark() {
        super(new RandomEnumValue<>(SharkBreed.class).getString(), new RandomEnumValue<>(AnimalCharacter.class).getString());
    }

    @SuppressWarnings("unused")
    public Shark(String breed, String character) {
        super(breed, character);
    }

    @Override
    public String toString() {
        return "{\"Shark\": {" + "\"breed\": \"" + getBreed() + "\", \"character\": \"" + getCharacter() + "\"}}";
    }

    @Override
    public void move() {
        System.out.println("The shark is swimming.");
    }

    @Override
    public void eat() {
        System.out.println("The shark is hunting fish and humans.");
    }

    @Override
    public void sound() {
        System.out.println("The shark is silent.");
    }

    @Override
    public void live() {
        move();
        eat();
        sound();
    }
}
