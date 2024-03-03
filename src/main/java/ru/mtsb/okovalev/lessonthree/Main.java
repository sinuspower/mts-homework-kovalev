package ru.mtsb.okovalev.lessonthree;

import java.util.ArrayList;

import ru.mtsb.okovalev.lessonthree.animals.Animal;

public class Main {
	@SuppressWarnings("all")
	public static void main(String[] args) {
		CreateAnimalsServiceImpl createAnimalsServiceImpl = new CreateAnimalsServiceImpl();
		ArrayList<Animal> byCreateAnimalsServiceCreate = createAnimalsServiceImpl.createDefault();
		for (int i = 0; i < byCreateAnimalsServiceCreate.size(); i++) {
			System.out.println(byCreateAnimalsServiceCreate.get(i));
		}

		System.out.println();
		ArrayList<Animal> byCreateAnimalsServiceImplCreate = createAnimalsServiceImpl.create();
		for (Animal animal : byCreateAnimalsServiceImplCreate) {
			System.out.println(animal);
		}

		System.out.println();
		ArrayList<Animal> byCreateAnimalsServiceImplCreateN = createAnimalsServiceImpl.create(12);
		byCreateAnimalsServiceImplCreateN.forEach(System.out::println);
	}
}
