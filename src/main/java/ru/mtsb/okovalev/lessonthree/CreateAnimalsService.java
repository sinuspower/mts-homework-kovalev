package ru.mtsb.okovalev.lessonthree;

import ru.mtsb.okovalev.lessonthree.animals.*;
import ru.mtsb.okovalev.lessonthree.animals.enums.AnimalCharacter;
import ru.mtsb.okovalev.lessonthree.animals.enums.AnimalName;
import ru.mtsb.okovalev.lessonthree.animals.enums.AnimalType;
import ru.mtsb.okovalev.lessonthree.animals.enums.WolfBreed;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Интерфейс содержит методы создания животных.
 */
public interface CreateAnimalsService {
    /**
     * Размер по умолчанию результирующего массива животных.
     */
    int DEFAULT_ANIMALS_COUNT = 10;

    /**
     * Возвращает массив псевдослучайных животных размера по умолчанию.
     * Для заполнения массива используется цикл while.
     * Записывает информацию о количестве созданных животных и использованном
     * для этого методе в стандартный поток вывода.
     *
     * @return массив псевдослучайных животных размера по умолчанию
     */
    default ArrayList<Animal> create() {
        ArrayList<Animal> animals = new ArrayList<>();

        int i = 0;
        while (i < DEFAULT_ANIMALS_COUNT) {
            animals.add(randomAnimal());
            i++;
        }

        return animals;
    }

    /**
     * Возвращает псевдослучайное животное псевдослучайного вида.
     * По умолчанию – волк породы "Red wolf" с характером "nervous".
     *
     * @return псевдослучаное животное
     */
    default Animal randomAnimal() {
        Animal randomAnimal;

        switch (AnimalType.getRandom()) {
            case WOLF:
                randomAnimal = new Wolf();
                break;
            case SHARK:
                randomAnimal = new Shark();
                break;
            case DOG:
                randomAnimal = new Dog();
                break;
            case CAT:
                randomAnimal = new Cat();
                break;
            default:
                randomAnimal = new Wolf(
                        WolfBreed.RED_WOLF.toString(),
                        AnimalCharacter.NERVOUS.toString(),
                        AnimalName.BABY.toString(),
                        LocalDate.now()
                );
                break;
        }

        return randomAnimal;
    }

    /**
     * Возвращает ассоциативный массив псевдослучайных животных,
     * содержащий количество объектов по умолчанию.
     *
     * @return Map(ключ - тип животного, значение - список псевдослучайных животных этого типа)
     */
    Map<String, List<Animal>> createMap();
}
