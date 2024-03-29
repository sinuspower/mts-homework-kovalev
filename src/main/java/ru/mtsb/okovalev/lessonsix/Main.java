package ru.mtsb.okovalev.lessonsix;

import ru.mtsb.okovalev.lessonthree.CreateAnimalsServiceImpl;
import ru.mtsb.okovalev.lessonthree.animals.Animal;
import ru.mtsb.okovalev.lessonthree.animals.Dog;
import ru.mtsb.okovalev.lessonthree.animals.enums.AnimalCharacter;
import ru.mtsb.okovalev.lessonthree.animals.enums.AnimalName;
import ru.mtsb.okovalev.lessonthree.animals.enums.DogBreed;
import ru.mtsb.okovalev.lessonthree.util.Representations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        CreateAnimalsServiceImpl createAnimalsServiceImpl = new CreateAnimalsServiceImpl();
        int n = 3;

        Map<String, List<Animal>> animalsMap = createAnimalsServiceImpl.createMap(n);
        System.out.println("\tanimalsMap: " + n + " animals created by CreateAnimalsServiceImpl.createMap(" + n + ")");
        System.out.println(Representations.asJson_MapStringListAnimal(animalsMap) + "\n");

        ArrayList<Animal> animalsList = createAnimalsServiceImpl.create(n);
        System.out.println("\tanimalsList: " + n + " animals created by CreateAnimalsServiceImpl.create(" + n + ")");
        System.out.println(Representations.asJson_ArrayListAnimal(animalsList) + "\n");

        AnimalsRepositoryImpl animalsRepositoryImpl = new AnimalsRepositoryImpl();
        System.out.println("\tAnimalsRepositoryImpl.findLeapYearNames(animalsList)\n" +
                animalsRepositoryImpl.findLeapYearNames(animalsList));

        System.out.println();
        int ageYearsBound = 10;
        System.out.println("\tAnimalsRepositoryImpl.findOlderAnimals(animalsList, " + ageYearsBound + ")\n" +
                Representations.asJson_MapAnimalInteger(animalsRepositoryImpl.findOlderAnimals(animalsList, ageYearsBound)));

        System.out.println();
        ArrayList<Animal> withDuplicates = withDuplicates();
        System.out.println("\twithDuplicates:\n" + Representations.asJson_ArrayListAnimal(withDuplicates));
        System.out.println("\tAnimalsRepositoryImpl.findDuplicates(withDuplicates)");
        Map<String, Integer> duplicates = animalsRepositoryImpl.findAllDuplicates(withDuplicates);
        System.out.println("\t" + duplicates);
    }

    private static ArrayList<Animal> withDuplicates() {
        ArrayList<Animal> result = new CreateAnimalsServiceImpl().create(3);

        result.add(new Dog(
                DogBreed.BULL_TERRIER.toString(),
                AnimalCharacter.AMBITIOUS.toString(),
                AnimalName.TUTU.toString(),
                LocalDate.now(), 99.99)
        );

        result.add(new Dog(
                DogBreed.BULL_TERRIER.toString(),
                AnimalCharacter.AMBITIOUS.toString(),
                AnimalName.TUTU.toString(),
                LocalDate.now(), 99.99)
        );

        result.add(new Dog(
                DogBreed.AFFENPINSCHER.toString(),
                AnimalCharacter.PHLEGMATIC.toString(),
                AnimalName.CUDDLES.toString(),
                LocalDate.now(), 190)
        );

        result.add(new Dog(
                DogBreed.BULL_TERRIER.toString(),
                AnimalCharacter.AMBITIOUS.toString(),
                AnimalName.TUTU.toString(),
                LocalDate.now(), 99.99)
        );

        result.add(new Dog(
                DogBreed.AFFENPINSCHER.toString(),
                AnimalCharacter.PHLEGMATIC.toString(),
                AnimalName.CUDDLES.toString(),
                LocalDate.now(), 190)
        );

        return result;
    }
}
