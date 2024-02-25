package ru.mtsb.okovalev.java.learning.lessonthree.animals;

import ru.mtsb.okovalev.java.learning.lessonthree.util.RandomEnumValue;

public class Shark extends Predator {
	@SuppressWarnings("unused")
	private enum Breed {
		ANGEL_SHARK("Angel shark"), BLACKTIP_SHARK("Blacktip shark"), BULL_SHARK("Bull shark"),
		GALAPAGOS_SHARK("Galapagos shark"), LEMON_SHARK("Lemon shark"), LEOPARD_SHARK("Leopard shark"),
		SANDBAR_SHARK("Sandbar shark"), WHALE_SHARK("Whale shark"), WHITE_SHARK("White shark"),
		ZEBRA_SHARK("Zebra shark");

		private final String printable;

		Breed(String printable) {
			this.printable = printable;
		}

		@Override
		public String toString() {
			return this.printable;
		}
	}

	public Shark() {
		super(new RandomEnumValue<>(Breed.class).getString(), new RandomEnumValue<>(Character.class).getString());
	}

	@SuppressWarnings("unused")
	public Shark(String breed, String character) {
		super(breed, character);
	}

	@Override
	public String toString() {
		return "Shark {" + "breed: " + getBreed() + ", character: " + getCharacter() + "}";
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
		System.out.println(this);
		move();
		eat();
		sound();
	}
}
