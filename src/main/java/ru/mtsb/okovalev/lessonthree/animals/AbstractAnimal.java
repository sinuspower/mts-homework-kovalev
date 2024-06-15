package ru.mtsb.okovalev.lessonthree.animals;

import com.fasterxml.jackson.annotation.*;
import ru.mtsb.okovalev.lessonnine.util.SecretInformationCache;
import ru.mtsb.okovalev.lessonthree.animals.enums.AnimalType;

import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;

/**
 * Базовая абстракция животного.
 */
@JsonPropertyOrder({"id", "secret", "type", "breed", "name", "character", "birthdate", "age"})
public abstract class AbstractAnimal implements Animal {
    @JsonProperty("id")
    protected UUID uuid;
    protected AnimalType type;
    protected String breed;
    protected String character;
    protected String name;
    @JsonFormat(pattern = "yyyy-MM-dd")
    protected LocalDate birthdate;
    @JsonIgnore
    protected String birthdateFormat = BIRTHDATE_FORMAT_DEFAULT;
    protected String secretInformation;

    /**
     * Путь к файлу с секретной информацией.
     */
    public static final Path SECRET_INFO_FILE_PATH = Path.of("resources/secretStore/secretInformation.txt");

    /**
     * Кеш секретной информации для предотвращения слишком частого обращения к файлу.
     */
    public static final SecretInformationCache SECRET_INFO_CACHE = new SecretInformationCache();

    static {
        try {
            SECRET_INFO_CACHE.init(SECRET_INFO_FILE_PATH);
        } catch (IOException e) {
            throw new RuntimeException("Can not initialize secret information cache: " + e.getMessage());
        }
    }

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
        this.uuid = UUID.randomUUID();
        this.type = type;
        this.secretInformation = getRandomSecretStringFromCache();
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
        this.uuid = UUID.randomUUID();
        this.type = type;
        this.breed = breed;
        this.character = character;
        this.name = name;
        this.birthdate = birthdate;
        this.secretInformation = getRandomSecretStringFromCache();
    }

    private String getRandomSecretStringFromCache() {
        var random = new Random();
        return SECRET_INFO_CACHE.get(random.nextInt(SECRET_INFO_CACHE.size()));
    }

    /**
     * Создаёт новый объект в виде копии заданного объекта.
     *
     * @param source Исходный объект для копирования
     */
    public AbstractAnimal(AbstractAnimal source) {
        this.uuid = UUID.randomUUID();
        this.type = source.type;
        this.breed = source.breed;
        this.character = source.character;
        this.name = source.name;
        this.birthdate = source.birthdate;
        this.birthdateFormat = source.birthdateFormat;
        this.secretInformation = source.secretInformation;
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
    @JsonProperty("age")
    public int getAgeYears() {
        if (Objects.isNull(birthdate)) {
            return 0;
        }

        return Period.between(birthdate, LocalDate.now()).getYears();
    }

    /**
     * Возвращает секретную информацию.
     *
     * @return секретная информация
     */
    @Override
    @JsonIgnore
    public String getSecretInformation() {
        return secretInformation;
    }

    @SuppressWarnings("unused")
    @JsonGetter("secret")
    private String getSecretInformationEncoded() {
        return Base64.getEncoder().encodeToString(secretInformation.getBytes());
    }

    @SuppressWarnings("unused")
    @JsonSetter
    private void setSecretInformationDecoded(@JsonProperty("secret") String secretInformationEncoded) {
        this.secretInformation = Arrays.toString(Base64.getDecoder().decode(secretInformationEncoded));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractAnimal)) return false;
        AbstractAnimal that = (AbstractAnimal) o;
        return getType() == that.getType() &&
                Objects.equals(this.uuid, that.uuid) &&
                Objects.equals(getBreed(), that.getBreed()) &&
                Objects.equals(getCharacter(), that.getCharacter()) &&
                Objects.equals(getName(), that.getName()) &&
                Objects.equals(getBirthdate(), that.getBirthdate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                uuid,
                getType(),
                getBreed(),
                getCharacter(),
                getName(),
                getBirthdate()
        );
    }
}
