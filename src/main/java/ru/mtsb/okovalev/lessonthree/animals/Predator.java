package ru.mtsb.okovalev.lessonthree.animals;

import ru.mtsb.okovalev.lessonthree.animals.enums.AnimalType;

/**
 * Дикое животное, хищник – расширение базовой абстракции AbstractAnimal.
 */
public abstract class Predator extends AbstractAnimal {
    /**
     * Создаёт дикого хищника с указанными параметрами.
     *
     * @param type      Тип
     * @param breed     Порода
     * @param character Характер
     * @param name      Кличка
     */
    public Predator(AnimalType type, String breed, String character, String name) {
        super(type, breed, character, name);
    }

    /**
     * Возвращает тип дикого хищника.
     *
     * @return тип дикого хищника
     */
    @Override
    public AnimalType getType() {
        return super.type;
    }

    /**
     * Возвращает породу дикого хищника.
     *
     * @return порода дикого хищника
     */
    @Override
    public String getBreed() {
        return super.breed;
    }

    /**
     * Возвращает характер дикого хищника.
     *
     * @return характер дикого хищника
     */
    @Override
    public String getCharacter() {
        return super.character;
    }

    /**
     * Возвращает кличку дикого хищника.
     *
     * @return кличка дикого хищника
     */
    @Override
    public String getName() {
        return super.name;
    }

    /**
     * Возвращает представление дикого животного в формате JSON.
     *
     * @return компактный (в одну строку) JSON, содержащий объект Predator со всеми полями класса Predator
     */
    @Override
    public String toString() {
        return "{"
                + "\"type\":\"" + getType() + "\","
                + "\"breed\":\"" + getBreed() + "\","
                + "\"character\":\"" + getCharacter() + "\","
                + "\"name\":\"" + getName() + "\""
                + "}";
    }
}
