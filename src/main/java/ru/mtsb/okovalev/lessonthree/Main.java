package ru.mtsb.okovalev.lessonthree;

import ru.mtsb.okovalev.lessonthree.animals.Animal;
import ru.mtsb.okovalev.lessonthree.animals.Cat;
import ru.mtsb.okovalev.lessonthree.animals.Dog;
import ru.mtsb.okovalev.lessonthree.animals.Shark;
import ru.mtsb.okovalev.lessonthree.animals.enums.AnimalCharacter;
import ru.mtsb.okovalev.lessonthree.animals.enums.AnimalName;
import ru.mtsb.okovalev.lessonthree.animals.enums.DogBreed;

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

        ArrayList<Animal> byCreateAnimalsServiceImplCreateN = createAnimalsServiceImpl.create(4);
        byCreateAnimalsServiceImplCreateN.forEach(System.out::println);
        System.out.println();

        System.out.println("\tCat created by constructor without parameters");
        Animal cat = new Cat();
        System.out.println(cat);
        System.out.println("\tcat.live()");
        cat.live();
        System.out.println();

        System.out.println("\tDog created by constructor with parameters");
        Animal dog = new Dog(DogBreed.BOXER.toString(), AnimalCharacter.NERVOUS.toString(), AnimalName.LOLA.toString(), 120.50);
        System.out.println(dog);
        System.out.println("\tdog.sound()");
        dog.sound();
        System.out.println();

        System.out.println("\tShark created by constructor without parameters");
        Animal shark = new Shark();
        System.out.println(shark);
        System.out.println("\tshark.eat()");
        shark.eat();
    }
}
