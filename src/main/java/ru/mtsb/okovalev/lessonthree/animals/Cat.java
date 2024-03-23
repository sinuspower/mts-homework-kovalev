package ru.mtsb.okovalev.lessonthree.animals;

import ru.mtsb.okovalev.lessonthree.animals.enums.AnimalType;

import java.time.LocalDate;

/**
 * Кошка.
 */
public class Cat extends Pet {
    private static final AnimalType TYPE = AnimalType.CAT;

    /**
     * Создаёт "пустую" кошку.
     */
    public Cat() {
        super(TYPE);
    }

    /**
     * Создаёт кошку с указанными параметрами.
     *
     * @param breed     Порода
     * @param character Характер
     * @param name      Кличка
     * @param birthdate Дата рождения
     * @param cost      Стоимость в USD в зоомагазине или питомнике
     */
    @SuppressWarnings("unused")
    public Cat(String breed, String character, String name, LocalDate birthdate, double cost) {
        super(TYPE, breed, character, name, birthdate, cost);
    }

    /**
     * Записывает описание типичного способа перемещения кошки в стандартный поток вывода.
     */
    @Override
    public void move() {
        System.out.println(getName() + " is lying on the sofa.");
    }

    /**
     * Записывает описание типичного способа питания кошки в стандартный поток вывода.
     */
    @Override
    public void eat() {
        System.out.println(getName() + " is eating Whiskas.");
    }

    /**
     * Записывает описание типичного звука, издаваемого кошкой, в стандартный поток вывода.
     */

    @Override
    public void sound() {
        System.out.println(getName() + " is mewing.");
    }

    /**
     * Записывает описание типичного образа жизни кошки в стандартный поток вывода
     * через вызов методов move(), eat() и sound().
     */
    @Override
    public void live() {
        move();
        eat();
        sound();
    }
}
