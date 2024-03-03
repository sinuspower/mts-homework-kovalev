package ru.mtsb.okovalev.lessonthree.animals;

import ru.mtsb.okovalev.lessonthree.animals.enums.AnimalCharacter;
import ru.mtsb.okovalev.lessonthree.animals.enums.AnimalName;
import ru.mtsb.okovalev.lessonthree.animals.enums.CatBreed;
import ru.mtsb.okovalev.lessonthree.util.RandomEnumValue;

import java.text.DecimalFormat;
import java.util.Random;

public class Cat extends Pet {
    public Cat() {
        super(new RandomEnumValue<>(CatBreed.class).getString(), new RandomEnumValue<>(AnimalCharacter.class).getString(),
                new RandomEnumValue<>(AnimalName.class).getString(), new Random().nextDouble());
        this.cost = this.cost * new Random().nextInt(super.COST_BOUND);
    }

    @SuppressWarnings("unused")
    public Cat(String breed, String character, String name, double cost) {
        super(breed, character, name, cost);
    }

    @Override
    public String toString() {
        return "{\"Cat\": {" + "\"breed\": \"" + getBreed() + "\", \"character\": \"" + getCharacter() + "\", \"name\": \"" + getName() + "\", \"cost\": \""
                + new DecimalFormat("$#0.00").format(getCost()) + "\"}}";
    }

    @Override
    public void move() {
        System.out.println(getName() + " is lying on the sofa.");
    }

    @Override
    public void eat() {
        System.out.println(getName() + " is eating Whiskas.");
    }

    @Override
    public void sound() {
        System.out.println(getName() + " is mewing.");
    }

    @Override
    public void live() {
        move();
        eat();
        sound();
    }
}
