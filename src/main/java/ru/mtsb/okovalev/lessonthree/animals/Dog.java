package ru.mtsb.okovalev.lessonthree.animals;

import ru.mtsb.okovalev.lessonthree.util.RandomEnumValue;

import java.text.DecimalFormat;
import java.util.Random;

public class Dog extends Pet {
    @SuppressWarnings("unused")
    private enum Breed {
        AFFENPINSCHER("Affenpinscher"), AKITA("Akita"), AUSTRALIAN_SHEPHERD("Australian Shepherd"),
        BASSET_HOUND("Basset Hound"), BEAGLE("Beagle"), BIEWER_TERRIER("Biewer Terrier"), BOXER("Boxer"),
        BULLDOG("Bulldog"), BULL_TERRIER("Bull Terrier"), CANE_CORSO("Cane Corso");

        private final String printable;

        Breed(String printable) {
            this.printable = printable;
        }

        @Override
        public String toString() {
            return this.printable;
        }
    }

    public Dog() {
        super(new RandomEnumValue<>(Breed.class).getString(), new RandomEnumValue<>(Character.class).getString(),
                new RandomEnumValue<>(Name.class).getString(), new Random().nextDouble());
        this.cost = this.cost * new Random().nextInt(COST_BOUND);
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
        System.out.println(this);
        move();
        eat();
        sound();
    }
}
