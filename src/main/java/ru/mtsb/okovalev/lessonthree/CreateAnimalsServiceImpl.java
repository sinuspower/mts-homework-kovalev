package ru.mtsb.okovalev.lessonthree;

import ru.mtsb.okovalev.lessonthree.animals.Animal;

import java.util.ArrayList;

/**
 * Имплементация интерфейса CreateAnimalsService.
 * Переопределяет метод create() интерфейса по умолчанию.
 * Добавляет собственный метод create(n) для создания n псевдослучайных животных.
 */
public class CreateAnimalsServiceImpl implements CreateAnimalsService {
    /**
     * Возвращает массив псевдослучайных животных размера по умолчанию.
     * Для заполнения массива используется цикл do-while.
     * Записывает информацию о количестве созданных животных и использованном
     * для этого методе в стандартный поток вывода.
     *
     * @return массив псевдослучайных животных размера по умолчанию
     */
    @Override
    public ArrayList<Animal> create() {
        ArrayList<Animal> animals = new ArrayList<>();

        int i = 0;
        do {
            animals.add(randomAnimal());
            i++;
        } while (i < DEFAULT_ANIMALS_COUNT);

        System.out.println("\t" + DEFAULT_ANIMALS_COUNT + " animals created by CreateAnimalsServiceImpl.create()");
        return animals;
    }

    /**
     * Возвращает массив псевдослучайных животных размера n.
     * Для заполнения массива используется цикл for.
     * Записывает информацию о количестве созданных животных и использованном
     * для этого методе в стандартный поток вывода.
     *
     * @param n Размер результирующего массива псевдослучайных животных
     * @return массив псевдослучайных животных размера n
     */
    public ArrayList<Animal> create(int n) {
        ArrayList<Animal> animals = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            animals.add(randomAnimal());
        }

        System.out.println("\t" + n + " animals created by CreateAnimalsServiceImpl.create(" + n + ")");
        return animals;
    }
}
