package ru.mtsb.okovalev.lessonthree.animals;

import ru.mtsb.okovalev.lessonthree.animals.enums.AnimalCharacter;
import ru.mtsb.okovalev.lessonthree.animals.enums.WolfBreed;
import ru.mtsb.okovalev.lessonthree.util.RandomEnumValue;

/**
 * Волк.
 */
public class Wolf extends Predator {
    /**
     * Создаёт волка псевдослучайной породы с псевдослучайным характером.
     */
    public Wolf() {
        super(new RandomEnumValue<>(WolfBreed.class).getString(), new RandomEnumValue<>(AnimalCharacter.class).getString());
    }

    /**
     * Создаёт волка с указанными параметрами.
     *
     * @param breed     Порода
     * @param character Характер
     */
    @SuppressWarnings("unused")
    public Wolf(String breed, String character) {
        super(breed, character);
    }

    /**
     * Возвращает представление волка в формате JSON.
     *
     * @return компактный (в одну строку) JSON, содержащий объект Wolf со всеми полями класса Wolf
     */
    @Override
    public String toString() {
        return "{\"Wolf\": {" + "\"breed\": \"" + getBreed() + "\", \"character\": \"" + getCharacter() + "\"}}";
    }

    /**
     * Записывает описание типичного способа перемещения волка в стандартный поток вывода.
     */
    @Override
    public void move() {
        System.out.println("The wolf is running.");
    }

    /**
     * Записывает описание типичного способа питания волка в стандартный поток вывода.
     */
    @Override
    public void eat() {
        System.out.println("The wolf is hunting large hoofed mammals.");
    }

    /**
     * Записывает описание типичного звука, издаваемого волком, в стандартный поток вывода.
     */
    @Override
    public void sound() {
        System.out.println("The wolf is howling.");
    }

    /**
     * Записывает описание типичного образа жизни волка в стандартный поток вывода
     * через вызов методов move(), eat() и sound().
     */
    @Override
    public void live() {
        move();
        eat();
        sound();
    }
}
