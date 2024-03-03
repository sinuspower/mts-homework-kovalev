package ru.mtsb.okovalev.lessonthree.animals;

import ru.mtsb.okovalev.lessonthree.animals.enums.AnimalCharacter;
import ru.mtsb.okovalev.lessonthree.animals.enums.AnimalName;
import ru.mtsb.okovalev.lessonthree.animals.enums.DogBreed;
import ru.mtsb.okovalev.lessonthree.util.RandomEnumValue;

import java.text.DecimalFormat;
import java.util.Random;

/**
 * Собака.
 */
public class Dog extends Pet {
    /**
     * Создаёт собаку псевдослучайной породы с псевдослучайным характером, кличкой и стоимостью.
     */
    public Dog() {
        super(new RandomEnumValue<>(DogBreed.class).getString(), new RandomEnumValue<>(AnimalCharacter.class).getString(),
                new RandomEnumValue<>(AnimalName.class).getString(), new Random().nextDouble());
        this.cost = this.cost * new Random().nextInt(super.COST_BOUND);
    }

    /**
     * Создаёт собаку с указанными параметрами.
     *
     * @param breed     Порода
     * @param character Характер
     * @param name      Кличка
     * @param cost      Стоимость в USD в зоомагазине или питомнике
     */
    @SuppressWarnings("unused")
    public Dog(String breed, String character, String name, double cost) {
        super(breed, character, name, cost);
    }

    /**
     * Возвращает представление собаки в формате JSON.
     *
     * @return компактный (в одну строку) JSON, содержащий объект Dog со всеми полями класса Dog
     */
    @Override
    public String toString() {
        return "{\"Dog\": {" + "\"breed\": \"" + getBreed() + "\", \"character\": \"" + getCharacter() + "\", \"name\": \"" + getName() + "\", \"cost\": \""
                + new DecimalFormat("$#0.00").format(getCost()) + "\"}}";
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
