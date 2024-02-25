package ru.mtsb.okovalev.java.learning.lessonthree;

import java.util.ArrayList;

import ru.mtsb.okovalev.java.learning.lessonthree.animals.Animal;

public class CreateAnimalsServiceImpl implements CreateAnimalsService {
	@Override
	public ArrayList<Animal> create() {
		ArrayList<Animal> animals = new ArrayList<>();

		int i = 0;
		do {
			animals.add(randomAnimal());
			i++;
		} while (i < DEFAULT_ANIMALS_COUNT);

		System.out.println(DEFAULT_ANIMALS_COUNT + " animals created by CreateAnimalsServiceImpl.create()");
		return animals;
	}

	public ArrayList<Animal> createDefault() {
		return CreateAnimalsService.super.create();
	}

	public ArrayList<Animal> create(int n) {
		ArrayList<Animal> animals = new ArrayList<>();

		for (int i = 0; i < n; i++) {
			animals.add(randomAnimal());
		}

		System.out.println(n + " animals created by CreateAnimalsServiceImpl.create(" + n + ")");
		return animals;
	}
}
