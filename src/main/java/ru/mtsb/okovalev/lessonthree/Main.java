package ru.mtsb.okovalev.lessonthree;

import ru.mtsb.okovalev.lessonthree.animals.Animal;
import ru.mtsb.okovalev.lessonthree.animals.Cat;
import ru.mtsb.okovalev.lessonthree.animals.Dog;
import ru.mtsb.okovalev.lessonthree.animals.Shark;
import ru.mtsb.okovalev.lessonthree.animals.enums.AnimalCharacter;
import ru.mtsb.okovalev.lessonthree.animals.enums.AnimalName;
import ru.mtsb.okovalev.lessonthree.animals.enums.DogBreed;
import ru.mtsb.okovalev.lessonthree.util.Animals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {
    @SuppressWarnings("all")
    public static void main(String[] args) {
        CreateAnimalsServiceImpl createAnimalsServiceImpl = new CreateAnimalsServiceImpl();
        ArrayList<Animal> byCreateAnimalsServiceCreate = new CreateAnimalsService() {
            @Override
            public Map<String, List<Animal>> createMap() {
                return null;
            }
        }.create();
        System.out.println("\t" + CreateAnimalsService.DEFAULT_ANIMALS_COUNT +
                " animals created by CreateAnimalsService.create()");
        System.out.println(Animals.asJson(byCreateAnimalsServiceCreate) + "\n");

        ArrayList<Animal> byCreateAnimalsServiceImplCreate = createAnimalsServiceImpl.create();
        System.out.println("\t" + CreateAnimalsService.DEFAULT_ANIMALS_COUNT +
                " animals created by CreateAnimalsServiceImpl.create()");
        System.out.println(Animals.asJson(byCreateAnimalsServiceImplCreate) + "\n");

        int n = 4;
        ArrayList<Animal> byCreateAnimalsServiceImplCreateN = createAnimalsServiceImpl.create(n);
        System.out.println("\t" + n + " animals created by CreateAnimalsServiceImpl.create(" + n + ")");
        System.out.println(Animals.asJson(byCreateAnimalsServiceImplCreateN) + "\n");

        System.out.println("\tCat created by constructor without parameters");
        Animal cat = new Cat();
        System.out.println(cat);
        System.out.println("\tcat.live()");
        cat.live();
        System.out.println();

        System.out.println("\tDog created by constructor with parameters");
        Animal dog = new Dog(DogBreed.BOXER.toString(), AnimalCharacter.NERVOUS.toString(), AnimalName.LOLA.toString(), LocalDate.now(), 120.50);
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
