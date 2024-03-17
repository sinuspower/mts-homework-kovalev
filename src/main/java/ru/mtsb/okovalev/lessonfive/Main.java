package ru.mtsb.okovalev.lessonfive;

import ru.mtsb.okovalev.lessonfive.exceptions.InvalidAnimalBirthdateException;
import ru.mtsb.okovalev.lessonfive.exceptions.InvalidAnimalException;
import ru.mtsb.okovalev.lessonthree.CreateAnimalsServiceImpl;
import ru.mtsb.okovalev.lessonthree.animals.Animal;

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
                throw new InvalidAnimalException("Can not check if animal was born in a leap year");
            }
        }
    }
}
