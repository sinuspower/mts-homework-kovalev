package ru.mtsb.okovalev.lessonthree;

import ru.mtsb.okovalev.lessonthree.animals.Animal;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Реализация интерфейса CreateAnimalsService.
 * Переопределяет метод create() интерфейса по умолчанию.
 * Добавляет собственный метод create(n) для создания n псевдослучайных животных.
 */
public class CreateAnimalsServiceImpl implements CreateAnimalsService {
    /**
     * Путь к log-файлу создаваемых животных.
     */
    private Path createAnimalsLogFilePath;

    /**
     * Задаёт значение по умолчанию пути к log-файлу создаваемых животных.
     */
    public CreateAnimalsServiceImpl() {
        this.createAnimalsLogFilePath = DEFAULT_CREATE_ANIMALS_LOG_FILE_PATH;
    }

    /**
     * Возвращает путь к log-файлу создаваемых животных.
     *
     * @return Текущий путь к файлу
     */
    public Path getCreateAnimalsLogFilePath() {
        return createAnimalsLogFilePath;
    }

    /**
     * Устанавливает новое значение пути к log-файлу создаваемых животных.
     *
     * @param path Новый путь к файлу
     */
    public void setCreateAnimalsLogFilePath(Path path) {
        this.createAnimalsLogFilePath = path;
    }

    /**
     * Возвращает массив псевдослучайных животных размера по умолчанию.
     * Для заполнения массива используется цикл do-while.
     * Записывает информацию о созданных животных в файл по пути DEFAULT_LOG_FILE_PATH.
     *
     * @return массив псевдослучайных животных размера по умолчанию
     * @throws IOException если произошло исключение во время записи лог-файла
     */
    @Override
    public ArrayList<Animal> create() throws IOException {
        ArrayList<Animal> animals = new ArrayList<>();

        int i = 0;
        do {
            animals.add(animalsFactory.getRandomAnimal());
            i++;
        } while (i < DEFAULT_ANIMALS_COUNT);

        appendLogFile(createAnimalsLogFilePath, "CreateAnimalsServiceImpl.create()", animals);

        return animals;
    }

    /**
     * Возвращает ассоциативный массив псевдослучайных животных,
     * содержащий количество объектов по умолчанию.
     * Записывает информацию о созданных животных в файл по пути DEFAULT_LOG_FILE_PATH.
     *
     * @return Map; ключ - тип животного, значение - список псевдослучайных животных этого типа
     * @throws IOException если произошло исключение во время записи лог-файла
     */
    @Override
    public Map<String, List<Animal>> createMap() throws IOException {
        return createMap(DEFAULT_ANIMALS_COUNT);
    }

    /**
     * Возвращает массив псевдослучайных животных размера n.
     * Для заполнения массива используется цикл for.
     * Записывает информацию о созданных животных в файл по пути DEFAULT_LOG_FILE_PATH.
     *
     * @param n Размер результирующего массива псевдослучайных животных
     * @return массив псевдослучайных животных размера n
     * @throws IOException если произошло исключение во время записи лог-файла
     */
    public ArrayList<Animal> create(int n) throws IOException {
        ArrayList<Animal> animals = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            animals.add(animalsFactory.getRandomAnimal());
        }

        appendLogFile(createAnimalsLogFilePath, "CreateAnimalsServiceImpl.create(n)", animals);

        return animals;
    }

    /**
     * Возвращает ассоциативный массив псевдослучайных животных,
     * содержащий в общей сложности заданное количество объектов.
     * Записывает информацию о созданных животных в файл по пути DEFAULT_LOG_FILE_PATH.
     *
     * @param n Количество животных, которых необходимо создать
     * @return Map; ключ - тип животного, значение - список псевдослучайных животных этого типа
     * @throws IOException если произошло исключение во время записи лог-файла
     */
    public Map<String, List<Animal>> createMap(int n) throws IOException {
        HashMap<String, List<Animal>> animals = new HashMap<>();

        Animal animal;
        ArrayList<Animal> animalsList;
        String animalType;
        for (int i = 0; i < n; i++) {
            animal = animalsFactory.getRandomAnimal();
            animalType = animal.getType().toString();

            if (animals.containsKey(animalType)) {
                animals.get(animalType).add(animal);
            } else {
                animalsList = new ArrayList<>();
                animalsList.add(animal);
                animals.put(animal.getType().toString(), animalsList);
            }

            appendLogFile(createAnimalsLogFilePath, "CreateAnimalsServiceImpl.createMap(n)", i, animal);
        }

        return animals;
    }
}
