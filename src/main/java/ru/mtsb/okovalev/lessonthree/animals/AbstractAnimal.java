package ru.mtsb.okovalev.lessonthree.animals;

/**
 * Базовая абстракция животного.
 */
public abstract class AbstractAnimal implements Animal {
    protected String breed;
    protected String character;

    /**
     * Задаёт базовые параметры экземпляров дочерних классов.
     *
     * @param breed     Порода животного
     * @param character Поведение (характер) животного
     */
    public AbstractAnimal(String breed, String character) {
        this.breed = breed;
        this.character = character;
    }

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
}
