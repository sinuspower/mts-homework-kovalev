package ru.mtsb.okovalev.java.learning.lessontwo;

public class Sum {
	public static void main(String[] args) {
		int n = args.length;
		int[] numbers = new int[n];

		for (int i = 0; i < n; i++) {
			numbers[i] = Integer.valueOf(args[i]);
		}

		System.out.println(sum(numbers));
	}

	private static int sum(int... numbers) {
		int sum = 0;
		for (int number : numbers) {
			sum += number;
		}

		return sum;
	}
}