package ru.mtsb.okovalev.lessonthree.util;

import java.util.Random;

/**
 * Provides methods for getting a random enum values.
 * 
 * @param <T> must be an extension of class Enum.
 */
public class RandomEnumValue<T extends Enum<T>> {
	private static final Random random = new Random();
	private final T[] values;

	public RandomEnumValue(Class<T> e) {
		values = e.getEnumConstants();
	}

	/**
	 * Returns a random enum value of type T.
	 * 
	 * @return Random enum value.
	 */
	public T get() {
		return values[random.nextInt(values.length)];
	}

	/**
	 * Returns a random enum value represented through the call of its toString()
	 * method.
	 * 
	 * @return String representation of random enum value.
	 */
	public String getString() {
		return values[random.nextInt(values.length)].toString();
	}
}
