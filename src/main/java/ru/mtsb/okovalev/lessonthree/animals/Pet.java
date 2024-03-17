package ru.mtsb.okovalev.lessonthree.animals;

import ru.mtsb.okovalev.lessonthree.animals.enums.AnimalType;

import java.text.DecimalFormat;

/**
 * Домашнее животное – расширение базовой абстракции AbstractAnimal.
 * Добавляет два свойства, присущих домашнему животному: кличку и стоимость.
 */
public abstract class Pet extends AbstractAnimal {
    protected double cost;

    /**
     * Максимальная стоимость домашнего животного в USD.
     */
    protected final int COST_BOUND = 10000;

    /**
     * Создаёт домашнее животное с указанными параметрами.
     *
     * @param type      Тип
     * @param breed     Порода
     * @param character Характер
     * @param name      Кличка
     * @param cost      Стоимость в USD в зоомагазине или питомнике
     */
    public Pet(AnimalType type, String breed, String character, String name, double cost) {
        super(type, breed, character, name);
        this.cost = cost;
    }

    /**
     * Возвращает тип домашнего животного.
     *
     * @return тип домашнего животного
     */
    @Override
    public AnimalType getType() {
        return super.type;
    }

    /**
     * Возвращает породу домашнего животного.
     *
     * @return порода домашнего животного
     */
    @Override
    public String getBreed() {
        return super.breed;
    }

    /**
     * Возвращает характер домашнего животного.
     *
     * @return характер домашнего животного
     */
    @Override
    public String getCharacter() {
        return super.character;
    }

    /**
     * Возвращает кличку домашнего животного.
     *
     * @return кличка домашнего животного
     */
    @Override
    public String getName() {
        return super.name;
    }

    /**
     * Возвращает стоимость домашнего животного в USD.
     *
     * @return стоимость домашнего животного в USD
     */
    public double getCost() {
        return cost;
    }

    /**
     * Возвращает представление домашнего животного в формате JSON.
     *
     * @return компактный (в одну строку) JSON, содержащий объект Pet со всеми полями класса Pet
     */
    @Override
    public String toString() {
        return "{"
                + "\"type\":\"" + getType() + "\","
                + "\"breed\":\"" + getBreed() + "\","
                + "\"character\":\"" + getCharacter() + "\","
                + "\"name\":\"" + getName() + "\","
                + "\"cost\":\"" + new DecimalFormat("$#0.00").format(getCost()) + "\""
                + "}";
    }
}
