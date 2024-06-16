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
 * Содержит набор статических методов для чтения и анализа файлов, создаваемых
 * в результате выполнения тех или иных методов в рамках проекта.
 */
public class ResultReader {
    /**
     * Выполняет чтение массива животных из файла, создаваемого методом
     * {@link AnimalsRepositoryImpl#findOlderAnimals(List, int) findOlderAnimals}.
     *
     * @return Массив животных из файла
     * @throws IOException если произошло исключение во время чтения файла
     */
    public static List<Animal> readFindOlderAnimalsJsonFile() throws IOException {
        Path source = AnimalsRepositoryImpl.DEFAULT_FIND_OLDER_ANIMALS_JSON_FILE_PATH;
        var file = new File(source.toString());

        return new ObjectMapper().readValue(file, new TypeReference<>() {
        });
    }

    /**
     * Возвращает количество строк в log-файле, создаваемом методами
     * {@link CreateAnimalsService} и {@link CreateAnimalsServiceImpl}
     *
     * @return Количество строк в файле
     * @throws IOException если произошло исключение во время чтения файла
     */
    public static long createAnimalsLogFileLinesCount() throws IOException {
        Path source = CreateAnimalsService.DEFAULT_CREATE_ANIMALS_LOG_FILE_PATH;

        try (Stream<String> s = Files.lines(source)) {
            return s.count();
        }
    }
}
