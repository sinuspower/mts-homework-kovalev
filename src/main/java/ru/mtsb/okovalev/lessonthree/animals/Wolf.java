package ru.mtsb.okovalev.lessonthree.animals;

import ru.mtsb.okovalev.lessonthree.util.RandomEnumValue;

public class Wolf extends Predator {
	@SuppressWarnings("unused")
	private enum Breed {
		ARCTIC_WOLF("Arctic wolf"), EASTERN_WOLF("Eastern wolf"), HIMALAYAN_WOLF("Himalayan wolf"),
		MEXICAN_WOLF("Mexican wolf"), RED_WOLF("Red wolf"), ARABIAN_WOLF("Arabian wolf"),
		EURASIAN_WOLF("Eurasian wolf"), INDIAN_WOLF("Indian wolf"), NORTHWESTERN_WOLF("Northwestern wolf"),
		BLACK_WOLF("Black wolf");

		private final String printable;

		Breed(String printable) {
			this.printable = printable;
		}

		@Override
		public String toString() {
			return this.printable;
		}
	}

	public Wolf() {
		super(new RandomEnumValue<>(Breed.class).getString(), new RandomEnumValue<>(Character.class).getString());
	}

	@SuppressWarnings("unused")
	public Wolf(String breed, String character) {
		super(breed, character);
	}

	@Override
	public String toString() {
		return "Wolf {" + "breed: " + getBreed() + ", character: " + getCharacter() + "}";
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
		System.out.println(this);
		move();
		eat();
		sound();
	}
}
