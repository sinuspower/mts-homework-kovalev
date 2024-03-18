package ru.mtsb.okovalev.lessonfive;

import ru.mtsb.okovalev.lessonfive.exceptions.InvalidAnimalBirthdateException;
import ru.mtsb.okovalev.lessonfive.exceptions.InvalidAnimalException;
import ru.mtsb.okovalev.lessonthree.CreateAnimalsServiceImpl;
import ru.mtsb.okovalev.lessonthree.animals.Animal;
import ru.mtsb.okovalev.lessonthree.animals.Dog;
import ru.mtsb.okovalev.lessonthree.animals.enums.AnimalCharacter;
import ru.mtsb.okovalev.lessonthree.animals.enums.AnimalName;
import ru.mtsb.okovalev.lessonthree.animals.enums.DogBreed;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        CreateAnimalsServiceImpl createAnimalsServiceImpl = new CreateAnimalsServiceImpl();
        SearchServiceImpl searchServiceImpl = new SearchServiceImpl();

        ArrayList<Animal> animals = createAnimalsServiceImpl.create();
        for (Animal animal : animals) {
            System.out.println(animal);
            System.out.print("\t");
            try {
                searchServiceImpl.checkLeapYearAnimal(animal);
            } catch (InvalidAnimalBirthdateException e) {
                throw new InvalidAnimalException("Can not check if animal was born in a leap year\n\t" +
                        e.getMessage());
            }
        }

        System.out.println("\n\tAnimal is null" +
                "\n\tsearchServiceImpl.checkLeapYearAnimal(animal) =>");
        try {
            searchServiceImpl.checkLeapYearAnimal(null);
        } catch (InvalidAnimalException | InvalidAnimalBirthdateException e) {
            System.out.println("Can not check if animal was born in a leap year\n\t" +
                    e.getMessage());
        }

        Animal dog = new Dog(
                DogBreed.BOXER.toString(),
                AnimalCharacter.NERVOUS.toString(),
                AnimalName.LOLA.toString(),
                null,
                120.50
        );
        System.out.println("\n\tAnimal.getBirthdate() is null\n" + dog +
                "\n\tsearchServiceImpl.checkLeapYearAnimal(animal) =>");
        try {
            searchServiceImpl.checkLeapYearAnimal(dog);
        } catch (InvalidAnimalException | InvalidAnimalBirthdateException e) {
            System.out.println("Can not check if animal was born in a leap year\n\t" +
                    e.getMessage());
        }
    }
}
