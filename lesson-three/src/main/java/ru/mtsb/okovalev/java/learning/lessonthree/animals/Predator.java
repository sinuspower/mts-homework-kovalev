package ru.mtsb.okovalev.java.learning.lessonthree.animals;

public abstract class Predator extends AbstractAnimal {
	public Predator(String breed, String character) {
		super(breed, character);
	}

	@Override
	public String getBreed() {
		return super.breed;
	}

	@Override
	public String getCharacter() {
		return super.character;
	}
}
