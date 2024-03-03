package ru.mtsb.okovalev.lessonthree.animals;

import ru.mtsb.okovalev.lessonthree.animals.enums.AnimalCharacter;
import ru.mtsb.okovalev.lessonthree.animals.enums.SharkBreed;
import ru.mtsb.okovalev.lessonthree.util.RandomEnumValue;

/**
 * Акула.
 */
public class Shark extends Predator {
    /**
     * Создаёт акулу псевдослучайной породы с псевдослучайным характером.
     */
    public Shark() {
        super(new RandomEnumValue<>(SharkBreed.class).getString(), new RandomEnumValue<>(AnimalCharacter.class).getString());
    }

    /**
     * Создаёт акулу с указанными параметрами.
     *
     * @param breed     Порода
     * @param character Характер
     */
    @SuppressWarnings("unused")
    public Shark(String breed, String character) {
        super(breed, character);
    }

    /**
     * Возвращает представление акулы в формате JSON.
     *
     * @return компактный (в одну строку) JSON, содержащий объект Shark со всеми полями класса Shark
     */
    @Override
    public String toString() {
        return "{\"Shark\": {" + "\"breed\": \"" + getBreed() + "\", \"character\": \"" + getCharacter() + "\"}}";
    }

    /**
     * Записывает описание типичного способа перемещения акулы в стандартный поток вывода.
     */
    @Override
    public void move() {
        System.out.println("The shark is swimming.");
    }

    /**
     * Записывает описание типичного способа питания акулы в стандартный поток вывода.
     */
    @Override
    public void eat() {
        System.out.println("The shark is hunting fish and humans.");
    }

    /**
     * Записывает описание типичного звука, издаваемого акулой, в стандартный поток вывода.
     */
    @Override
    public void sound() {
        System.out.println("The shark is silent.");
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
