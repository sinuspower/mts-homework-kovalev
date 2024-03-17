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
