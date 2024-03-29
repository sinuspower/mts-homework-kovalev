package ru.mtsb.okovalev.lessonthree.animals;

import ru.mtsb.okovalev.lessonthree.animals.enums.AnimalType;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

/**
 * Базовая абстракция животного.
 */
public abstract class AbstractAnimal implements Animal {
    protected AnimalType type;
    protected String breed;
    protected String character;
    protected String name;
    protected LocalDate birthdate;
    protected String birthdateFormat = BIRTHDATE_FORMAT_DEFAULT;

    /**
     * Паттерн форматирования даты рождения по умолчанию.
     */
    protected static final String BIRTHDATE_FORMAT_DEFAULT = "dd-MM-yyyy";

    /**
     * Задаёт "пустые" базовые параметры экземпляров дочерних классов,
     * заполняя только тип животного.
     *
     * @param type Тип животного
     */
    public AbstractAnimal(AnimalType type) {
        this.type = type;
    }

    /**
     * Задаёт базовые параметры экземпляров дочерних классов.
     *
     * @param type      Тип животного
     * @param breed     Порода животного
     * @param character Поведение (характер) животного
     * @param name      Кличка животного
     * @param birthdate Дата рождения животного
     */
    public AbstractAnimal(AnimalType type, String breed, String character, String name, LocalDate birthdate) {
        this.type = type;
        this.breed = breed;
        this.character = character;
        this.name = name;
        this.birthdate = birthdate;
    }

    /**
     * Создаёт новый объект в виде копии заданного объекта.
     *
     * @param source Исходный объект для копирования
     */
    public AbstractAnimal(AbstractAnimal source) {
        this.type = source.type;
        this.breed = source.breed;
        this.character = source.character;
        this.name = source.name;
        this.birthdate = source.birthdate;
    }

    /**
     * Устанавливает дату рождения животного.
     *
     * @param birthdate Новая дата рождения
     * @throws IllegalArgumentException если новая дата рождения больше текущей даты
     */
    public void setBirthdate(LocalDate birthdate) throws IllegalArgumentException {
        if (birthdate.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Tried to set birthdate greater than today");
        }

        this.birthdate = birthdate;
    }

    /**
     * Возвращает возраст животного в годах.
     *
     * @return возраст животного в годах
     */
    public int getAgeYears() {
        if (Objects.isNull(birthdate)) {
            return 0;
        }

        return Period.between(birthdate, LocalDate.now()).getYears();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractAnimal)) return false;
        AbstractAnimal that = (AbstractAnimal) o;
        return getType() == that.getType() &&
                Objects.equals(getBreed(), that.getBreed()) &&
                Objects.equals(getCharacter(), that.getCharacter()) &&
                Objects.equals(getName(), that.getName()) &&
                Objects.equals(getBirthdate(), that.getBirthdate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                getType(),
                getBreed(),
                getCharacter(),
                getName(),
                getBirthdate()
        );
    }
}
