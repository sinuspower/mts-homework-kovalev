package ru.mtsb.okovalev.lessonthree.animals;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import ru.mtsb.okovalev.lessonthree.animals.enums.AnimalType;

import java.time.LocalDate;

/**
 * Собака.
 */
@JsonDeserialize(as = Dog.class)
public class Dog extends Pet {
    private static final AnimalType TYPE = AnimalType.DOG;

    /**
     * Создаёт "пустую" собаку.
     */
    @SuppressWarnings("unused")
    public Dog() {
        super(TYPE);
    }

    /**
     * Создаёт собаку с указанными параметрами.
     *
     * @param breed     Порода
     * @param character Характер
     * @param name      Кличка
     * @param birthdate Дата рождения
     * @param cost      Стоимость в USD в зоомагазине или питомнике
     */
    public Dog(String breed, String character, String name, LocalDate birthdate, double cost) {
        super(TYPE, breed, character, name, birthdate, cost);
    }

    /**
     * Создаёт собаку как копию другой собаки.
     *
     * @param source Исходная собака для копирования.
     */
    @SuppressWarnings("unused")
    public Dog(Dog source) {
        super(source);
    }

    /**
     * Записывает описание типичного способа перемещения собаки в стандартный поток вывода.
     */
    @Override
    public void move() {
        System.out.println(getName() + " is running and jumping.");
    }

    /**
     * Записывает описание типичного способа питания собаки в стандартный поток вывода.
     */
    @Override
    public void eat() {
        System.out.println(getName() + " is eating Chappie.");
    }

    /**
     * Записывает описание типичного звука, издаваемого собакой, в стандартный поток вывода.
     */
    @Override
    public void sound() {
        System.out.println(getName() + " is barking.");
    }

    /**
     * Записывает описание типичного образа жизни собаки в стандартный поток вывода
     * через вызов методов move(), eat() и sound().
     */
    @Override
    public void live() {
        move();
        eat();
        sound();
    }
}
