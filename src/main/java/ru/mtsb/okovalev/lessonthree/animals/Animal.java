package ru.mtsb.okovalev.lessonthree.animals;

import ru.mtsb.okovalev.lessonthree.animals.enums.AnimalType;

import java.time.LocalDate;

/**
 * Интерфейс содержит методы, описывающие поведение животного.
 */
public interface Animal {
    /**
     * Возвращает тип животного.
     *
     * @return тип животного
     */
    AnimalType getType();

    /**
     * Возвращает породу животного.
     *
     * @return порода животного
     */
    String getBreed();

    /**
     * Возвращает характер животного.
     *
     * @return характер животного
     */
    String getCharacter();

    /**
     * Возвращает кличку животного.
     *
     * @return кличка животного
     */
    String getName();

    /**
     * Возвращает дату рождения животного.
     *
     * @return дата рождения животного
     */
    LocalDate getBirthdate();

    /**
     * Возвращает стоимость животного в USD.
     * Метод по умолчанию для животных, которые не могут иметь стоимости.
     *
     * @return нулевая стоимость животного в USD
     */
    default double getCost() {
        return 0.0;
    }

    /**
     * Устанавливает дату рождения животного.
     *
     * @param birthdate Новая дата рождения
     * @throws IllegalArgumentException если новая дата рождения больше текущей даты
     */
    void setBirthdate(LocalDate birthdate) throws IllegalArgumentException;

    /**
     * Возвращает формат строкового представления даты рождения животного.
     *
     * @return формат строкового представления даты рождения животного
     */
    String getBirthdateFormat();

    /**
     * Устанавливает формат строкового представления даты рождения животного.
     *
     * @param format формат строкового представления даты рождения животного
     */
    @SuppressWarnings("unused")
    void setBirthdateFormat(String format);

    /**
     * Возвращает отформатированное строковое представление даты рождения животного.
     *
     * @return дата рождения животного в формате this.getBirthdateFormat()
     */
    String getBirthdateFormatted();

    /**
     * Возвращает возраст животного в годах.
     *
     * @return возраст животного в годах
     */
    int getAgeYears();

    /**
     * Движение.
     */
    void move();

    /**
     * Питание.
     */
    void eat();

    /**
     * Издаваемый звук.
     */
    void sound();

    /**
     * Образ жизни.
     */
    void live();
}
