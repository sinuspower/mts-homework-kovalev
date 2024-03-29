package ru.mtsb.okovalev.lessonthree.animals;

import ru.mtsb.okovalev.lessonthree.animals.enums.AnimalType;

import java.time.LocalDate;

/**
 * Акула.
 */
public class Shark extends Predator {
    private static final AnimalType TYPE = AnimalType.SHARK;

    /**
     * Создаёт "пустую" акулу.
     */
    public Shark() {
        super(TYPE);
    }

    /**
     * Создаёт акулу с указанными параметрами.
     *
     * @param breed     Порода
     * @param character Характер
     * @param name      Кличка
     * @param birthdate Дата рождения
     */
    public Shark(String breed, String character, String name, LocalDate birthdate) {
        super(TYPE, breed, character, name, birthdate);
    }

    /**
     * Создаёт акулу как копию другой акулы.
     *
     * @param source Исходная акула для копирования.
     */
    public Shark(Shark source) {
        super(source);
    }

    /**
     * Записывает описание типичного способа перемещения акулы в стандартный поток вывода.
     */
    @Override
    public void move() {
        System.out.println(getName() + " is swimming.");
    }

    /**
     * Записывает описание типичного способа питания акулы в стандартный поток вывода.
     */
    @Override
    public void eat() {
        System.out.println(getName() + " is hunting fish and humans.");
    }

    /**
     * Записывает описание типичного звука, издаваемого акулой, в стандартный поток вывода.
     */
    @Override
    public void sound() {
        System.out.println(getName() + " is silent.");
    }

    /**
     * Записывает описание типичного образа жизни акулы в стандартный поток вывода
     * через вызов методов move(), eat() и sound().
     */
    @Override
    public void live() {
        move();
        eat();
        sound();
    }
}
