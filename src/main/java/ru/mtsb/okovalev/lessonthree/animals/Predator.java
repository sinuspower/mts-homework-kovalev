package ru.mtsb.okovalev.lessonthree.animals;

import ru.mtsb.okovalev.lessonthree.animals.enums.AnimalType;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Дикое животное, хищник – расширение базовой абстракции AbstractAnimal.
 */
public abstract class Predator extends AbstractAnimal {
    /**
     * Создаёт "пустого" дикого хищника - указывается только тип.
     *
     * @param type Тип животного
     */
    @SuppressWarnings("unused")
    public Predator(AnimalType type) {
        super(type);
    }

    /**
     * Создаёт дикого хищника с указанными параметрами.
     *
     * @param type      Тип
     * @param breed     Порода
     * @param character Характер
     * @param name      Кличка
     * @param birthdate Дата рождения
     */
    public Predator(AnimalType type, String breed, String character, String name, LocalDate birthdate) {
        super(type, breed, character, name, birthdate);
    }

    /**
     * Создаёт дикого хищника как копию другого дикого хищника.
     *
     * @param source Исходный дикий хищник для копирования.
     */
    public Predator(Predator source) {
        super(source);
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
     * Возвращает дату рождения дикого хищника.
     *
     * @return дата рождения дикого хищника
     */
    @Override
    public LocalDate getBirthdate() {
        return super.birthdate;
    }

    /**
     * Возвращает формат даты рождения дикого хищника.
     *
     * @return формат строкового представления даты рождения дикого хищника
     */
    @Override
    public String getBirthdateFormat() {
        return super.birthdateFormat;
    }

    /**
     * Устанавливает формат даты рождения дикого хищника.
     *
     * @param format формат строкового представления даты рождения дикого хищника
     * @throws IllegalArgumentException если параметр format содержит неверный паттерн форматирования даты
     */
    @Override
    public void setBirthdateFormat(String format) throws IllegalArgumentException {
        try {
            DateTimeFormatter.ofPattern(format);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e);
        }

        this.birthdateFormat = format;
    }

    /**
     * Возвращает отформатированное строковое представление даты рождения дикого хищника.
     *
     * @return дата рождения дикого хищника в формате this.birthdateFormat
     */
    @Override
    public String getBirthdateFormatted() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(getBirthdateFormat());
        return super.birthdate.format(dtf);
    }

    /**
     * Возвращает представление дикого хищника в формате JSON.
     *
     * @return компактный (в одну строку) JSON, содержащий объект Predator со всеми полями класса Predator
     */
    @Override
    public String toString() {
        return "{"
                + "\"type\":" + (this.type == null ? "null," : "\"" + getType() + "\",")
                + "\"breed\":" + (this.breed == null ? "null," : "\"" + getBreed() + "\",")
                + "\"character\":" + (this.character == null ? "null," : "\"" + getCharacter() + "\",")
                + "\"name\":" + (this.name == null ? "null," : "\"" + getName() + "\",")
                + "\"birthdate\":" + (this.birthdate == null ? "null" : "\"" + getBirthdateFormatted() + "\"")
                + "}";
    }
}
