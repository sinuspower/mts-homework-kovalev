package ru.mtsb.okovalev.lessonthree.animals;

import ru.mtsb.okovalev.lessonthree.animals.enums.AnimalCharacter;
import ru.mtsb.okovalev.lessonthree.animals.enums.AnimalName;
import ru.mtsb.okovalev.lessonthree.animals.enums.AnimalType;
import ru.mtsb.okovalev.lessonthree.animals.enums.SharkBreed;
import ru.mtsb.okovalev.lessonthree.util.RandomEnumValue;

import java.time.LocalDate;
import java.util.Random;

/**
 * Акула.
 */
public class Shark extends Predator {
    private static final AnimalType TYPE = AnimalType.SHARK;

    /**
     * Создаёт акулу псевдослучайной породы с псевдослучайным характером.
     */
    public Shark() {
        super(TYPE, new RandomEnumValue<>(SharkBreed.class).getString(),
                new RandomEnumValue<>(AnimalCharacter.class).getString(),
                new RandomEnumValue<>(AnimalName.class).getString(),
                LocalDate.now().minusDays(new Random().nextInt(AbstractAnimal.BIRTHDATE_DAYS_BOUND))
        );
    }

    /**
     * Создаёт акулу с указанными параметрами.
     *
     * @param breed     Порода
     * @param character Характер
     * @param name      Кличка
     * @param birthdate Дата рождения
     */
    @SuppressWarnings("unused")
    public Shark(String breed, String character, String name, LocalDate birthdate) {
        super(TYPE, breed, character, name, birthdate);
    }

    /**
     * Записывает описание типичного способа перемещения акулы в стандартный поток вывода.
     */
    @Override
    public void move() {
        System.out.println(getName() + " is swimming.");
    }

    /**
     * Записывает описание типичного способа питания акулы в стандартный поток вывода.
     */
    @Override
    public void eat() {
        System.out.println(getName() + " is hunting fish and humans.");
    }

    /**
     * Записывает описание типичного звука, издаваемого акулой, в стандартный поток вывода.
     */
    @Override
    public void sound() {
        System.out.println(getName() + " is silent.");
    }

    /**
     * Записывает описание типичного образа жизни акулы в стандартный поток вывода
     * через вызов методов move(), eat() и sound().
     */
    @Override
    public void live() {
        move();
        eat();
        sound();
    }
}
