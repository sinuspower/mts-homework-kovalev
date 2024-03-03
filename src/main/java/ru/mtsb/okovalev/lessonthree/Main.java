package ru.mtsb.okovalev.lessonthree;

import ru.mtsb.okovalev.lessonthree.animals.Animal;

import java.util.ArrayList;

public class Main {
    @SuppressWarnings("all")
    public static void main(String[] args) {
        ArrayList<Animal> byCreateAnimalsServiceCreate = new CreateAnimalsService() {
        }.create();
        for (int i = 0; i < byCreateAnimalsServiceCreate.size(); i++) {
            System.out.println(byCreateAnimalsServiceCreate.get(i));
        }

        System.out.println();
        CreateAnimalsServiceImpl createAnimalsServiceImpl = new CreateAnimalsServiceImpl();
        ArrayList<Animal> byCreateAnimalsServiceImplCreate = createAnimalsServiceImpl.create();
        for (Animal animal : byCreateAnimalsServiceImplCreate) {
            System.out.println(animal);
        }

        System.out.println();
        ArrayList<Animal> byCreateAnimalsServiceImplCreateN = createAnimalsServiceImpl.create(12);
        byCreateAnimalsServiceImplCreateN.forEach(System.out::println);
    }
}
