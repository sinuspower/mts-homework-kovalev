package ru.mtsb.okovalev.lessonthree.animals;

import ru.mtsb.okovalev.lessonthree.animals.enums.AnimalType;

import java.time.LocalDate;

/**
 * Базовая абстракция животного.
 */
public abstract class AbstractAnimal implements Animal {
    protected AnimalType type;
    protected String breed;
    protected String character;
    protected String name;
    protected LocalDate birthdate;
    protected String birthdateFormat = BIRTHDATE_FORMAT_DEFAULT;

    /**
     * Максимальный возраст животного в днях.
     * Может использоваться для генерации псевдослучайной даты рождения.
     */
    protected static final int BIRTHDATE_DAYS_BOUND = 10000;
    /**
     * Паттерн форматирования даты рождения по умолчанию.
     */
    protected static final String BIRTHDATE_FORMAT_DEFAULT = "dd-MM-yyyy";

    /**
     * Задаёт базовые параметры экземпляров дочерних классов.
     *
     * @param type      Тип животного
     * @param breed     Порода животного
     * @param character Поведение (характер) животного
     * @param name      Кличка животного
     * @param birthdate Дата рождения животного
     */
    public AbstractAnimal(AnimalType type, String breed, String character, String name, LocalDate birthdate) {
        this.type = type;
        this.breed = breed;
        this.character = character;
        this.name = name;
        this.birthdate = birthdate;
    }
}
