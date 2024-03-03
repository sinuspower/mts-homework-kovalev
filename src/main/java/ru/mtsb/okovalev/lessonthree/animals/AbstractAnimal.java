package ru.mtsb.okovalev.lessonthree.animals;

public abstract class AbstractAnimal implements Animal {
    protected String breed;
    protected String character;

    public AbstractAnimal(String breed, String character) {
        this.breed = breed;
        this.character = character;
    }

    protected abstract String getBreed();

    protected abstract String getCharacter();
}
