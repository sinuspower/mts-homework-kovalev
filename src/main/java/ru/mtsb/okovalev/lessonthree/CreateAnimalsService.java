package ru.mtsb.okovalev.lessonthree;

import ru.mtsb.okovalev.lessonthree.animals.Animal;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
     * Путь по умолчанию к файлу с логом создания животных.
     */
    Path DEFAULT_LOG_FILE_PATH = Path.of("resources/animals/logData.txt");

    /**
     * Формат даты-времени для лог-файла.
     */
    String LOG_FILE_DATETIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";

    AnimalsFactory animalsFactory = new AnimalsFactory();

    /**
     * Возвращает массив псевдослучайных животных размера по умолчанию.
     * Для заполнения массива используется цикл while.
     * Записывает информацию о созданных животных в файл по пути DEFAULT_LOG_FILE_PATH.
     *
     * @return массив псевдослучайных животных размера по умолчанию
     * @throws IOException если произошло исключение во время записи лог-файла
     */
    default ArrayList<Animal> create() throws IOException {
        ArrayList<Animal> animals = new ArrayList<>();

        int i = 0;
        while (i < DEFAULT_ANIMALS_COUNT) {
            animals.add(animalsFactory.getRandomAnimal());
            i++;
        }

        appendLogFile(DEFAULT_LOG_FILE_PATH, "CreateAnimalsService.create()", animals);

        return animals;
    }

    /**
     * Записывает список животных в файл по указанному пути в режиме APPEND.
     *
     * @param path    Путь к файлу для записи
     * @param method  Вызывающий метод
     * @param animals Список животных
     * @throws IOException если произошло исключение во время записи файла
     */
    default void appendLogFile(Path path, String method, ArrayList<Animal> animals) throws IOException {
        for (int i = 0; i < animals.size(); i++) {
            Animal animal = animals.get(i);
            appendLogFile(path, method, i, animal);
        }

        Files.writeString(path, "\n", StandardOpenOption.APPEND);
    }

    /**
     * Записывает строку "[{ISO_8601 timestamp} {method} {counter} {animal}]"
     * в файл по указанному пути в режиме APPEND.
     *
     * @param path    Путь к файлу для записи
     * @param method  Вызывающий метод
     * @param counter Локальный относительно вызывающего метода номер записи
     * @param animal  Животное
     * @throws IOException если произошло исключение во время записи лог-файла
     */
    default void appendLogFile(Path path, String method, int counter, Animal animal) throws IOException {
        Path parent = path.getParent();
        if (Files.notExists(parent)) {
            Files.createDirectories(parent);
        }

        var dtf = DateTimeFormatter.ofPattern(LOG_FILE_DATETIME_FORMAT);

        Files.writeString(path,
                String.format("[%s] %s %08d %s\n", LocalDateTime.now().format(dtf), method, counter, animal),
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND);
    }

    /**
     * Возвращает ассоциативный массив псевдослучайных животных,
     * содержащий количество объектов по умолчанию.
     * Записывает информацию о созданных животных в файл по пути DEFAULT_LOG_FILE_PATH.
     *
     * @return Map; ключ - тип животного, значение - список псевдослучайных животных этого типа
     * @throws IOException если произошло исключение во время записи лог-файла
     */
    Map<String, List<Animal>> createMap() throws IOException;
}
