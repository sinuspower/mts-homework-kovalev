package ru.mtsb.okovalev.lessonthree.animals;

import ru.mtsb.okovalev.lessonthree.animals.enums.AnimalType;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Домашнее животное – расширение базовой абстракции AbstractAnimal.
 * Добавляет два свойства, присущих домашнему животному: кличку и стоимость.
 */
public abstract class Pet extends AbstractAnimal {
    protected double cost;

    /**
     * Создаёт "пустое" домашнее животное - указывается только тип.
     *
     * @param type Тип животного
     */
    public Pet(AnimalType type) {
        super(type);
    }

    /**
     * Создаёт домашнее животное с указанными параметрами.
     *
     * @param type      Тип
     * @param breed     Порода
     * @param character Характер
     * @param name      Кличка
     * @param birthdate Дата рождения
     * @param cost      Стоимость в USD в зоомагазине или питомнике
     */
    public Pet(AnimalType type, String breed, String character, String name, LocalDate birthdate, double cost) {
        super(type, breed, character, name, birthdate);
        this.cost = cost;
    }

    /**
     * Создаёт домашнее животное как копию другого домашнего животного.
     *
     * @param source Исходное домашнее животное для копирования.
     */
    public Pet(Pet source) {
        super(source);
        this.cost = source.cost;
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
     * Возвращает дату рождения домашнего животного.
     *
     * @return дата рождения домашнего животного
     */
    @Override
    public LocalDate getBirthdate() {
        return super.birthdate;
    }

    /**
     * Возвращает секретную информацию.
     *
     * @return секретная информация
     */
    @Override
    public String getSecretInformation() {
        return super.secretInformation;
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
     * Возвращает формат даты рождения домашнего животного.
     *
     * @return формат строкового представления даты рождения домашнего животного
     */
    @Override
    public String getBirthdateFormat() {
        return super.birthdateFormat;
    }

    /**
     * Устанавливает формат даты рождения домашнего животного.
     *
     * @param format формат строкового представления даты рождения домашнего животного
     * @throws IllegalArgumentException если параметр format содержит неверный паттерн форматирования даты
     */
    @Override
    public void setBirthdateFormat(String format) throws IllegalArgumentException {
        try {
            DateTimeFormatter.ofPattern(format);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Format string is wrong for date-time formatting");
        }

        this.birthdateFormat = format;
    }

    /**
     * Возвращает отформатированное строковое представление даты рождения домашнего животного.
     *
     * @return дата рождения домашнего животного в формате this.birthdateFormat
     */
    @Override
    public String getBirthdateFormatted() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(getBirthdateFormat());
        return super.birthdate.format(dtf);
    }

    /**
     * Возвращает представление домашнего животного в формате JSON.
     *
     * @return компактный (в одну строку) JSON, содержащий объект Pet со всеми полями класса Pet
     */
    @Override
    public String toString() {
        return "{"
                + "\"type\":" + (this.type == null ? "null," : "\"" + getType() + "\",")
                + "\"breed\":" + (this.breed == null ? "null," : "\"" + getBreed() + "\",")
                + "\"character\":" + (this.character == null ? "null," : "\"" + getCharacter() + "\",")
                + "\"name\":" + (this.name == null ? "null," : "\"" + getName() + "\",")
                + "\"birthdate\":" + (this.birthdate == null ? "null," : "\"" + getBirthdateFormatted() + "\",")
                + "\"cost\":\"" + new DecimalFormat("$#0.00").format(getCost()) + "\""
                + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pet)) return false;
        if (!super.equals(o)) return false;
        Pet pet = (Pet) o;
        return Double.compare(getCost(), pet.getCost()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getCost());
    }
}
