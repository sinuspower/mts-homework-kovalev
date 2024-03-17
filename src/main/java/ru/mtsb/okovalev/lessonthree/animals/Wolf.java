package ru.mtsb.okovalev.lessonthree.animals;

import ru.mtsb.okovalev.lessonthree.animals.enums.AnimalCharacter;
import ru.mtsb.okovalev.lessonthree.animals.enums.AnimalName;
import ru.mtsb.okovalev.lessonthree.animals.enums.AnimalType;
import ru.mtsb.okovalev.lessonthree.animals.enums.WolfBreed;
import ru.mtsb.okovalev.lessonthree.util.RandomEnumValue;

/**
 * Волк.
 */
public class Wolf extends Predator {
    private static final AnimalType TYPE = AnimalType.WOLF;

    /**
     * Создаёт волка псевдослучайной породы с псевдослучайным характером.
     */
    public Wolf() {
        super(TYPE, new RandomEnumValue<>(WolfBreed.class).getString(),
                new RandomEnumValue<>(AnimalCharacter.class).getString(),
                new RandomEnumValue<>(AnimalName.class).getString()
        );
    }

    /**
     * Создаёт волка с указанными параметрами.
     *
     * @param breed     Порода
     * @param character Характер
     * @param name      Кличка
     */
    @SuppressWarnings("unused")
    public Wolf(String breed, String character, String name) {
        super(TYPE, breed, character, name);
    }

    /**
     * Записывает описание типичного способа перемещения волка в стандартный поток вывода.
     */
    @Override
    public void move() {
        System.out.println(getName() + " is running.");
    }

    /**
     * Записывает описание типичного способа питания волка в стандартный поток вывода.
     */
    @Override
    public void eat() {
        System.out.println(getName() + "is hunting large hoofed mammals.");
    }

    /**
     * Записывает описание типичного звука, издаваемого волком, в стандартный поток вывода.
     */
    @Override
    public void sound() {
        System.out.println(getName() + " is howling.");
    }

    /**
     * Записывает описание типичного образа жизни волка в стандартный поток вывода
     * через вызов методов move(), eat() и sound().
     */
    @Override
    public void live() {
        move();
        eat();
        sound();
    }
}
