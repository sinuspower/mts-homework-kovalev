package ru.mtsb.okovalev.lessonthree.animals;

import ru.mtsb.okovalev.lessonthree.animals.enums.AnimalCharacter;
import ru.mtsb.okovalev.lessonthree.animals.enums.AnimalName;
import ru.mtsb.okovalev.lessonthree.animals.enums.DogBreed;
import ru.mtsb.okovalev.lessonthree.util.RandomEnumValue;

import java.text.DecimalFormat;
import java.util.Random;

public class Dog extends Pet {
    public Dog() {
        super(new RandomEnumValue<>(DogBreed.class).getString(), new RandomEnumValue<>(AnimalCharacter.class).getString(),
                new RandomEnumValue<>(AnimalName.class).getString(), new Random().nextDouble());
        this.cost = this.cost * new Random().nextInt(super.COST_BOUND);
    }

    @SuppressWarnings("unused")
    public Dog(String breed, String character, String name, double cost) {
        super(breed, character, name, cost);
    }

    @Override
    public String toString() {
        return "{\"Dog\": {" + "\"breed\": \"" + getBreed() + "\", \"character\": \"" + getCharacter() + "\", \"name\": \"" + getName() + "\", \"cost\": \""
                + new DecimalFormat("$#0.00").format(getCost()) + "\"}}";
    }

    @Override
    public void move() {
        System.out.println(getName() + " is running and jumping.");
    }

    @Override
    public void eat() {
        System.out.println(getName() + " is eating Chappie.");
    }

    @Override
    public void sound() {
        System.out.println(getName() + " is barking.");
    }

    @Override
    public void live() {
        move();
        eat();
        sound();
    }
}
