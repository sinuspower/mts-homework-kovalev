package ru.mtsb.okovalev.lessonseven;

import ru.mtsb.okovalev.lessonsix.AnimalsRepositoryImpl;
import ru.mtsb.okovalev.lessonthree.CreateAnimalsServiceImpl;
import ru.mtsb.okovalev.lessonthree.animals.Animal;
import ru.mtsb.okovalev.lessonthree.util.Representations;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        CreateAnimalsServiceImpl createAnimalsServiceImpl = new CreateAnimalsServiceImpl();
        int n = 10;

        ArrayList<Animal> animalsList = createAnimalsServiceImpl.create(n);
        System.out.println("\tanimalsList: " + n + " animals created by CreateAnimalsServiceImpl.create(" + n + ")");
        System.out.println(Representations.asJson_ArrayListAnimal(animalsList) + "\n");

        AnimalsRepositoryImpl animalsRepositoryImpl = new AnimalsRepositoryImpl();
        System.out.println("\tAnimalsRepositoryImpl.findLeapYearNames(animalsList)\n" +
                animalsRepositoryImpl.findLeapYearNames(animalsList));
    }
}
