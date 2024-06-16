package ru.mtsb.okovalev.lessonthree;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ru.mtsb.okovalev.lessonthree.animals.Animal;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class CreateAnimalsServiceImplTest {
    private final Path testCreateAnimalsLogFilePath =
            Path.of("resources/test/animals/createAnimalsLog.txt");

    @AfterEach
    void cleanup() throws IOException {
        Files.deleteIfExists(testCreateAnimalsLogFilePath);
    }

    @ParameterizedTest(name = "{0} animals")
    @ValueSource(ints = {12, 10, 108, 1000})
    @DisplayName("CreateAnimalsServiceImpl.create() - check createAnimalsLogFile")
    public void create_checkCreateAnimalsLogFile(int n) {
        var createAnimalsServiceImpl = new CreateAnimalsServiceImpl();
        createAnimalsServiceImpl.setCreateAnimalsLogFilePath(testCreateAnimalsLogFilePath);

        AtomicReference<ArrayList<Animal>> animalsReference = new AtomicReference<>();
        assertDoesNotThrow(() -> animalsReference.set(createAnimalsServiceImpl.create(n)));
        ArrayList<Animal> animals = animalsReference.get();

        AtomicReference<List<String>> logStringsReference = new AtomicReference<>();
        assertDoesNotThrow(() -> {
            List<String> logStrings;
            try (Stream<String> s = Files.lines(testCreateAnimalsLogFilePath)) {
                logStrings = s.collect(Collectors.toList());
            }
            logStringsReference.set(logStrings);
        });
        ArrayList<String> logStrings = (ArrayList<String>) logStringsReference.get();

        assertEquals(animals.size(), logStrings.size());

        for (int i = 0; i < animals.size(); i++) {
            assertTrue(
                    logStrings.get(i).contains(animals.get(i).getType().toString()) &&
                            logStrings.get(i).contains(animals.get(i).getBreed()) &&
                            logStrings.get(i).contains(animals.get(i).getCharacter()) &&
                            logStrings.get(i).contains(animals.get(i).getName()) &&
                            logStrings.get(i).contains(animals.get(i).getBirthdateFormatted())
            );
        }
    }
}
