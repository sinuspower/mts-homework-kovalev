package ru.mtsb.okovalev.lessonnine;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.mtsb.okovalev.lessonnine.util.ResultReader;
import ru.mtsb.okovalev.lessonsix.AnimalsRepositoryImpl;
import ru.mtsb.okovalev.lessonthree.CreateAnimalsServiceImpl;
import ru.mtsb.okovalev.lessonthree.animals.Animal;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class ResultReaderTest {
    private final Path testCreateAnimalsLogFilePath =
            Path.of("resources/test/animals/createAnimalsLog.txt");

    private final Path testFindOlderAnimalsJsonFilePath =
            Path.of("resources/test/results/findOlderAnimals.json");

    private final ResultReader resultReader = new ResultReader(
            testCreateAnimalsLogFilePath,
            testFindOlderAnimalsJsonFilePath
    );

    @AfterEach
    void cleanup() throws IOException {
        Files.deleteIfExists(testCreateAnimalsLogFilePath);
    }

    @Test
    @DisplayName("ResultReader - no files")
    public void readFindOlderAnimalsJsonFile_noFiles() {
        var resultReader = new ResultReader(
                Path.of("notExists/" + testCreateAnimalsLogFilePath),
                Path.of("notExists/" + testFindOlderAnimalsJsonFilePath)
        );

        IOException thrown = assertThrows(
                IOException.class,
                resultReader::createAnimalsLogFileLinesCount
        );
        assertEquals("notExists\\resources\\test\\animals\\createAnimalsLog.txt", thrown.getMessage());

        thrown = assertThrows(
                IOException.class,
                resultReader::readFindOlderAnimalsJsonFile
        );
        assertTrue(thrown.getMessage().startsWith("notExists\\resources\\test\\results\\findOlderAnimals.json"));
    }

    @Test
    @DisplayName("ResultReader.readFindOlderAnimalsJsonFile")
    public void readFindOlderAnimalsJsonFile() {
        int n = 100;

        var createAnimalsServiceImpl = new CreateAnimalsServiceImpl();
        createAnimalsServiceImpl.setCreateAnimalsLogFilePath(testCreateAnimalsLogFilePath);
        AtomicReference<ArrayList<Animal>> animalsReference = new AtomicReference<>();
        assertDoesNotThrow(() -> animalsReference.set(createAnimalsServiceImpl.create(n)));
        ArrayList<Animal> animals = animalsReference.get();

        var animalsRepositoryImpl = new AnimalsRepositoryImpl();
        animalsRepositoryImpl.setFindOlderAnimalsJsonFilePath(testFindOlderAnimalsJsonFilePath);
        AtomicReference<Set<Animal>> expectedAnimalsReference = new AtomicReference<>();
        assertDoesNotThrow(() -> expectedAnimalsReference.set(animalsRepositoryImpl.findOlderAnimals(animals, 10).keySet()));
        ArrayList<Animal> expectedAnimals = new ArrayList<>(expectedAnimalsReference.get());

        AtomicReference<List<Animal>> actualAnimalsReference = new AtomicReference<>();
        assertDoesNotThrow(() -> actualAnimalsReference.set(resultReader.readFindOlderAnimalsJsonFile()));
        ArrayList<Animal> actualAnimals = (ArrayList<Animal>) actualAnimalsReference.get();

        assertEquals(expectedAnimals, actualAnimals);

        for (int i = 0; i < expectedAnimals.size(); i++) { // secretInformation must be decoded from base64
            assertEquals(
                    expectedAnimals.get(i).getSecretInformation(),
                    actualAnimals.get(i).getSecretInformation()
            );
        }
    }

    @Test
    @DisplayName("ResultReader.createAnimalsLogFileLinesCount")
    public void createAnimalsLogFileLinesCount() {
        var createAnimalsServiceImpl = new CreateAnimalsServiceImpl();
        createAnimalsServiceImpl.setCreateAnimalsLogFilePath(testCreateAnimalsLogFilePath);

        assertDoesNotThrow(() -> createAnimalsServiceImpl.create(12));
        assertDoesNotThrow(() -> {
            try (Stream<String> s = Files.lines(testCreateAnimalsLogFilePath)) {
                assertEquals(s.count(), resultReader.createAnimalsLogFileLinesCount());
            }
        });
    }
}
