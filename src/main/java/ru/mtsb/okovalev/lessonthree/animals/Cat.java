package ru.mtsb.okovalev.lessonthree.animals;

import ru.mtsb.okovalev.lessonthree.animals.enums.AnimalCharacter;
import ru.mtsb.okovalev.lessonthree.animals.enums.AnimalName;
import ru.mtsb.okovalev.lessonthree.animals.enums.CatBreed;
import ru.mtsb.okovalev.lessonthree.util.RandomEnumValue;

import java.text.DecimalFormat;
import java.util.Random;

/**
 * Кошка.
 */
public class Cat extends Pet {
    /**
     * Создаёт кошку псевдослучайной породы с псевдослучайным характером, кличкой и стоимостью.
     */
    public Cat() {
        super(new RandomEnumValue<>(CatBreed.class).getString(), new RandomEnumValue<>(AnimalCharacter.class).getString(),
                new RandomEnumValue<>(AnimalName.class).getString(), new Random().nextDouble());
        this.cost = this.cost * new Random().nextInt(super.COST_BOUND);
    }

    /**
     * Создаёт кошку с указанными параметрами.
     *
     * @param breed     Порода
     * @param character Характер
     * @param name      Кличка
     * @param cost      Стоимость в USD в зоомагазине или питомнике
     */
    @SuppressWarnings("unused")
    public Cat(String breed, String character, String name, double cost) {
        super(breed, character, name, cost);
    }

    /**
     * Возвращает представление кошки в формате JSON.
     *
     * @return компактный (в одну строку) JSON, содержащий объект Cat со всеми полями класса Cat
     */
    @Override
    public String toString() {
        return "{\"Cat\": {" + "\"breed\": \"" + getBreed() + "\", \"character\": \"" + getCharacter() + "\", \"name\": \"" + getName() + "\", \"cost\": \""
                + new DecimalFormat("$#0.00").format(getCost()) + "\"}}";
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
