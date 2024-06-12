package ru.mtsb.okovalev.lessonnine;

import ru.mtsb.okovalev.lessonthree.CreateAnimalsServiceImpl;
import ru.mtsb.okovalev.lessonthree.animals.Animal;
import ru.mtsb.okovalev.lessonthree.util.Representations;

import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        var createAnimalsServiceImpl = new CreateAnimalsServiceImpl();
        int n = 4;

        try {
            ArrayList<Animal> animals = createAnimalsServiceImpl.create(n);
            System.out.println("\t" + n + " animals created by CreateAnimalsServiceImpl.create(" + n + ")");
            System.out.println(Representations.asJson_ListAnimal(animals) + "\n");
        } catch (IOException e) {
            System.out.println("Can not write file: " + e.getMessage());
        }
    }
}
