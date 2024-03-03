package ru.mtsb.okovalev.lessonthree.animals;

public abstract class AbstractAnimal implements Animal {
	protected String breed;
	protected String character;

	@SuppressWarnings("unused")
	protected enum Character {
		AMBITIOUS("ambitious"), AMORPHOUS("amorphous"), APATHETIC("apathetic"), BLOOD("blood"), CHOLERIC("choleric"),
		NERVOUS("nervous"), PASSIONATE("passionate"), PHLEGMATIC("phlegmatic"), SENTIMENTAL("sentimental"),
		STRONG("strong");

		private final String printable;

		Character(String printable) {
			this.printable = printable;
		}

		@Override
		public String toString() {
			return this.printable;
		}
	}

	public AbstractAnimal(String breed, String character) {
		this.breed = breed;
		this.character = character;
	}

	protected abstract String getBreed();

	protected abstract String getCharacter();
}
