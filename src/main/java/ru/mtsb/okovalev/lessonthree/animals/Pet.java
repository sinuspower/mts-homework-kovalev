package ru.mtsb.okovalev.lessonthree.animals;

/**
 * Домашнее животное – расширение базовой абстракции AbstractAnimal.
 * Добавляет два свойства, присущих домашнему животному: кличку и стоимость.
 */
public abstract class Pet extends AbstractAnimal {
    protected String name;
    protected double cost;

    /**
     * Максимальная стоимость домашнего животного в USD.
     */
    protected final int COST_BOUND = 10000;

    /**
     * Создаёт домашнее животное с указанными параметрами.
     *
     * @param breed     Порода
     * @param character Характер
     * @param name      Кличка
     * @param cost      Стоимость в USD в зоомагазине или питомнике
     */
    public Pet(String breed, String character, String name, double cost) {
        super(breed, character);
        this.name = name;
        this.cost = cost;
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
    public String getName() {
        return name;
    }

    /**
     * Возвращает стоимость домашнего животного в USD.
     *
     * @return стоимость домашнего животного в USD
     */
    public double getCost() {
        return cost;
    }
}
