package ru.mtsb.okovalev.lessonthree;

import ru.mtsb.okovalev.lessonthree.animals.Animal;

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

    AnimalsFactory animalsFactory = new AnimalsFactory();

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
            animals.add(animalsFactory.getRandomAnimal());
            i++;
        }

        return animals;
    }

    /**
     * Возвращает ассоциативный массив псевдослучайных животных,
     * содержащий количество объектов по умолчанию.
     *
     * @return Map(ключ - тип животного, значение - список псевдослучайных животных этого типа)
     */
    Map<String, List<Animal>> createMap();
}
