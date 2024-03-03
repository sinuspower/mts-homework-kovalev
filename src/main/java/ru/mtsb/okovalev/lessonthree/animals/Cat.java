package ru.mtsb.okovalev.lessonthree.animals;

import java.text.DecimalFormat;
import java.util.Random;

import ru.mtsb.okovalev.lessonthree.util.RandomEnumValue;

public class Cat extends Pet {
	@SuppressWarnings("unused")
	private enum Breed {
		BALINESE("Balinese"), BIRMAN("Birman"), BURMESE("Burmese"), BURMILLA("Burmilla"), CHARTREUX("Chartreux"),
		CORNISH_REX("Cornish Rex"), JAVANESE("Javanese"), PERSIAN("Persian"), SIBERIAN("Siberian"), SPHYNX("Sphynx");

		private final String printable;

		Breed(String printable) {
			this.printable = printable;
		}

		@Override
		public String toString() {
			return this.printable;
		}
	}

	public Cat() {
		super(new RandomEnumValue<>(Breed.class).getString(), new RandomEnumValue<>(Character.class).getString(),
				new RandomEnumValue<>(Name.class).getString(), new Random().nextDouble());
		this.cost = this.cost * new Random().nextInt(COST_BOUND);
	}

	@SuppressWarnings("unused")
	public Cat(String breed, String character, String name, double cost) {
		super(breed, character, name, cost);
	}

	@Override
	public String toString() {
		return "Cat {" + "breed: " + getBreed() + ", character: " + getCharacter() + ", name: " + getName() + ", cost: "
				+ new DecimalFormat("$#0.00").format(getCost()) + "}";
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
		System.out.println(this);
		move();
		eat();
		sound();
	}
}
