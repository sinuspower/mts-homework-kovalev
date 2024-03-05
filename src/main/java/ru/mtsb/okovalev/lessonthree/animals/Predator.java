package ru.mtsb.okovalev.lessonthree.animals;

/**
 * Дикое животное, хищник – расширение базовой абстракции AbstractAnimal.
 */
public abstract class Predator extends AbstractAnimal {
    /**
     * Создаёт дикого хищника с указанными параметрами.
     *
     * @param breed     Порода
     * @param character Характер
     */
    public Predator(String breed, String character) {
        super(breed, character);
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
}
