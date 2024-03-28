package ru.mtsb.okovalev.lessonseven;

import ru.mtsb.okovalev.lessonsix.AnimalsRepositoryImpl;
import ru.mtsb.okovalev.lessonthree.CreateAnimalsServiceImpl;
import ru.mtsb.okovalev.lessonthree.animals.Animal;
import ru.mtsb.okovalev.lessonthree.util.Representations;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        CreateAnimalsServiceImpl createAnimalsServiceImpl = new CreateAnimalsServiceImpl();
        int n = 5;

        ArrayList<Animal> animalsList = createAnimalsServiceImpl.create(n);
        System.out.println("\tanimalsList: " + n + " animals created by CreateAnimalsServiceImpl.create(" + n + ")");
        System.out.println(Representations.asJson_ListAnimal(animalsList) + "\n");

        AnimalsRepositoryImpl animalsRepositoryImpl = new AnimalsRepositoryImpl();
        System.out.println("\tAnimalsRepositoryImpl.findLeapYearNames(animalsList)\n" +
                animalsRepositoryImpl.findLeapYearNames(animalsList));

        System.out.println();
        int ageYearsBound = 10;
        System.out.println("\tAnimalsRepositoryImpl.findOlderAnimals(animalsList, " + ageYearsBound + ")\n" +
                Representations.asJson_MapAnimalInteger(animalsRepositoryImpl.findOlderAnimals(animalsList, ageYearsBound)));

        System.out.println();
        ArrayList<Animal> withDuplicates = Helper.getAnimalsListWithDuplicates();
        System.out.println("\twithDuplicates:\n" + Representations.asJson_ListAnimal(withDuplicates));
        System.out.println("\tAnimalsRepositoryImpl.findDuplicates(withDuplicates)");
        Map<String, List<Animal>> duplicates = animalsRepositoryImpl.findAllDuplicates(withDuplicates);
        System.out.println(Representations.asJson_MapStringListAnimal(duplicates));

        System.out.println("\n\tAnimalsRepositoryImpl.findAverageAge(animalsList)");
        animalsRepositoryImpl.findAverageAge(animalsList);

        System.out.println("\n\tAnimalsRepositoryImpl.findOldAndExpensive(animalsList)");
        List<Animal> oldAndExpensive = animalsRepositoryImpl.findOldAndExpensive(animalsList);
        System.out.println(Representations.asJson_ListAnimal(oldAndExpensive));

        System.out.println("\n\tAnimalsRepositoryImpl.findMinConstAnimals(animalsList)\n" +
                animalsRepositoryImpl.findMinConstAnimals(animalsList));
    }
}
