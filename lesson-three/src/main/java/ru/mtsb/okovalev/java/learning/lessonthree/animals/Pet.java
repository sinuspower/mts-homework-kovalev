package ru.mtsb.okovalev.java.learning.lessonthree.animals;

public abstract class Pet extends AbstractAnimal {
	protected String name;
	protected double cost;

	protected final int COST_BOUND = 10000;

	@SuppressWarnings("unused")
	protected enum Name {
		FLOWER("Flower"), BAMBI("Bambi"), CUDDLES("Cuddles"), LOLA("Lola"), TWEETY("Tweety"), ANGEL("Angel"),
		CESAR("Cesar"), DAISY("Daisy"), TUTU("Tutu"), BABY("Baby");

		private final String printable;

		Name(String printable) {
			this.printable = printable;
		}

		@Override
		public String toString() {
			return this.printable;
		}
	}

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
		return this.name;
	}

	public double getCost() {
		return this.cost;
	}
}
