package ru.mtsb.okovalev.lessonnine.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.mtsb.okovalev.lessonsix.AnimalsRepositoryImpl;
import ru.mtsb.okovalev.lessonthree.CreateAnimalsService;
import ru.mtsb.okovalev.lessonthree.CreateAnimalsServiceImpl;
import ru.mtsb.okovalev.lessonthree.animals.Animal;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

/**
 * Содержит набор методов для чтения и анализа файлов, создаваемых
 * в результате выполнения тех или иных методов в рамках проекта.
 */
public class ResultReader {
    /**
     * Путь к log-файлу создаваемых животных.
     */
    private final Path createAnimalsLogFilePath;

    /**
     * Путь к результирующему файлу метода {@link AnimalsRepositoryImpl#findOlderAnimals(List, int)}.
     */
    private final Path findOlderAnimalsJsonFilePath;

    /**
     * Создаёт объект {@link ResultReader} с заданными путями к файлам.
     *
     * @param createAnimalsLogFilePath     Путь к log-файлу создаваемых животных
     * @param findOlderAnimalsJsonFilePath Путь к результирующему файлу метода {@link AnimalsRepositoryImpl#findOlderAnimals(List, int)}
     */
    public ResultReader(Path createAnimalsLogFilePath, Path findOlderAnimalsJsonFilePath) {
        this.createAnimalsLogFilePath = createAnimalsLogFilePath;
        this.findOlderAnimalsJsonFilePath = findOlderAnimalsJsonFilePath;
    }

    /**
     * Выполняет чтение массива животных из файла, создаваемого методом
     * {@link AnimalsRepositoryImpl#findOlderAnimals(List, int) findOlderAnimals}.
     *
     * @return Массив животных из файла
     * @throws IOException если произошло исключение во время чтения файла
     */
    public List<Animal> readFindOlderAnimalsJsonFile() throws IOException {
        return new ObjectMapper().readValue(
                new File(findOlderAnimalsJsonFilePath.toString()),
                new TypeReference<>() {
                }
        );
    }

    /**
     * Возвращает количество строк в log-файле, создаваемом методами
     * {@link CreateAnimalsService} и {@link CreateAnimalsServiceImpl}
     *
     * @return Количество строк в файле
     * @throws IOException если произошло исключение во время чтения файла
     */
    public long createAnimalsLogFileLinesCount() throws IOException {
        try (Stream<String> s = Files.lines(createAnimalsLogFilePath)) {
            return s.count();
        }
    }
}
