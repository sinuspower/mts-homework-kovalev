package ru.mtsb.okovalev.lessonsix;

import ru.mtsb.okovalev.lessonthree.CreateAnimalsServiceImpl;
import ru.mtsb.okovalev.lessonthree.animals.Animal;

import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        CreateAnimalsServiceImpl createAnimalsServiceImpl = new CreateAnimalsServiceImpl();
        int n = 10;
        Map<String, List<Animal>> animals = createAnimalsServiceImpl.createMap(n);
        System.out.println("\t" + n + " animals created by CreateAnimalsServiceImpl.createMap(" + n + ")");
        System.out.println(createAnimalsServiceImpl.asJson(animals) + "\n");
    }
}
