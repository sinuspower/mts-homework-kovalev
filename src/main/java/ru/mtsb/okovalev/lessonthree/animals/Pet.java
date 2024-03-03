package ru.mtsb.okovalev.lessonthree.animals;

public abstract class Pet extends AbstractAnimal {
    protected String name;
    protected double cost;

    protected final int COST_BOUND = 10000;

    public Pet(String breed, String character, String name, double cost) {
        super(breed, character);
        this.name = name;
        this.cost = cost;
    }

    @Override
    public String getBreed() {
        return super.breed;
    }

    @Override
    public String getCharacter() {
        return super.character;
    }

    public String getName() {
        return name;
    }

    public double getCost() {
        return cost;
    }
}
