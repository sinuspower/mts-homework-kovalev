package ru.mtsb.okovalev.lessonthree.animals;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import ru.mtsb.okovalev.lessonthree.animals.enums.AnimalType;

import java.time.LocalDate;

/**
 * Волк.
 */
@JsonDeserialize(as = Wolf.class)
public class Wolf extends Predator {
    private static final AnimalType TYPE = AnimalType.WOLF;

    /**
     * Создаёт "пустого" волка.
     */
    @SuppressWarnings("unused")
    public Wolf() {
        super(TYPE);
    }

    /**
     * Создаёт волка с указанными параметрами.
     *
     * @param breed     Порода
     * @param character Характер
     * @param name      Кличка
     * @param birthdate Дата рождения
     */
    public Wolf(String breed, String character, String name, LocalDate birthdate) {
        super(TYPE, breed, character, name, birthdate);
    }

    /**
     * Создаёт волка как копию другого волка.
     *
     * @param source Исходный волк для копирования.
     */
    @SuppressWarnings("unused")
    public Wolf(Wolf source) {
        super(source);
    }

    /**
     * Записывает описание типичного способа перемещения волка в стандартный поток вывода.
     */
    @Override
    public void move() {
        System.out.println(getName() + " is running.");
    }

    /**
     * Записывает описание типичного способа питания волка в стандартный поток вывода.
     */
    @Override
    public void eat() {
        System.out.println(getName() + "is hunting large hoofed mammals.");
    }

    /**
     * Записывает описание типичного звука, издаваемого волком, в стандартный поток вывода.
     */
    @Override
    public void sound() {
        System.out.println(getName() + " is howling.");
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
