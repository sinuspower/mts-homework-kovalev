package ru.mtsb.okovalev.lessonthree.animals;

import ru.mtsb.okovalev.lessonthree.animals.enums.AnimalType;

/**
 * Базовая абстракция животного.
 */
public abstract class AbstractAnimal implements Animal {
    protected AnimalType type;
    protected String breed;
    protected String character;
    protected String name;

    /**
     * Задаёт базовые параметры экземпляров дочерних классов.
     *
     * @param breed     Порода животного
     * @param character Поведение (характер) животного
     */
    public AbstractAnimal(AnimalType type, String breed, String character, String name) {
        this.type = type;
        this.breed = breed;
        this.character = character;
        this.name = name;
    }

    /**
     * Возвращает тип животного. Должен быть имплементирован в классе-наследнике.
     *
     * @return тип животного
     */
    protected abstract AnimalType getType();

    /**
     * Возвращает породу животного. Должен быть имплементирован в классе-наследнике.
     *
     * @return порода животного
     */
    protected abstract String getBreed();

    /**
     * Возвращает характер животного. Должен быть имплементирован в классе-наследнике.
     *
     * @return характер животного
     */
    protected abstract String getCharacter();

    /**
     * Возвращает кличку животного. Должен быть имплементирован в классе-наследнике.
     *
     * @return кличка животного
     */
    protected abstract String getName();
}
