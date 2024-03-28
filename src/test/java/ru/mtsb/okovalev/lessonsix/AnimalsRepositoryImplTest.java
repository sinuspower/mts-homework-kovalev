package ru.mtsb.okovalev.lessonsix;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import ru.mtsb.okovalev.Constants;
import ru.mtsb.okovalev.lessonthree.animals.Animal;
import ru.mtsb.okovalev.lessonthree.animals.Shark;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AnimalsRepositoryImplTest {
    private final AnimalsRepository animalsRepositoryImpl = new AnimalsRepositoryImpl();
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final List<Animal> constantsCatSharkDogWolf = List.of(
            Constants.CAT, Constants.SHARK, Constants.DOG, Constants.WOLF);

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    void tearDown() {
        System.setOut(standardOut);
    }

    @ParameterizedTest(name = "List<Animal> is {0}")
    @NullSource
    @DisplayName("AnimalsRepositoryImpl.findLeapYearNames - list is null")
    void findLeapYearNames_null(List<Animal> animals) {
        assertEquals(
                Constants.EMPTY_MAP_STRING,
                animalsRepositoryImpl.findLeapYearNames(animals).toString()
        );
    }

    @Test
    @DisplayName("AnimalsRepositoryImpl.findLeapYearNames - list is empty")
    void findLeapYearNames_empty() {
        assertEquals(
                Constants.EMPTY_MAP_STRING,
                animalsRepositoryImpl.findLeapYearNames(new ArrayList<>()).toString()
        );
    }

    @Test
    @DisplayName("AnimalsRepositoryImpl.findLeapYearNames - no results")
    void findLeapYearNames_noResults() {
        List<Animal> animals = List.of(Constants.CAT, Constants.SHARK);
        assertEquals(
                Constants.EMPTY_MAP_STRING,
                animalsRepositoryImpl.findLeapYearNames(animals).toString()
        );
    }

    @Test
    @DisplayName("AnimalsRepositoryImpl.findLeapYearNames - normal")
    void findLeapYearNames() {
        assertEquals(
                "{Wolf Cesar=[2020-03-24], Dog Cuddles=[2020-03-24]}",
                animalsRepositoryImpl.findLeapYearNames(constantsCatSharkDogWolf).toString()
        );
    }

    @ParameterizedTest(name = "List<Animal> is {0}")
    @NullSource
    @DisplayName("AnimalsRepositoryImpl.findOlderAnimals - list is null")
    void findOlderAnimals_null(List<Animal> animals) {
        assertEquals(
                Constants.EMPTY_MAP_STRING,
                animalsRepositoryImpl.findOlderAnimals(animals, 18).toString()
        );
    }

    @Test
    @DisplayName("AnimalsRepositoryImpl.findOlderAnimals - list is empty")
    void findOlderAnimals_empty() {
        assertEquals(
                Constants.EMPTY_MAP_STRING,
                animalsRepositoryImpl.findOlderAnimals(new ArrayList<>(), 18).toString()
        );
    }

    @Test
    @DisplayName("AnimalsRepositoryImpl.findOlderAnimals - no one is older")
    void findOlderAnimals_noOlder() {
        assertEquals(
                "{{\"type\":\"Shark\",\"breed\":\"Bull shark\",\"character\":\"sentimental\",\"name\":\"Daisy\",\"birthdate\":\"20-04-1991\"}=32}",
                animalsRepositoryImpl.findOlderAnimals(constantsCatSharkDogWolf, 40).toString()
        );
    }

    @Test
    @DisplayName("AnimalsRepositoryImpl.findOlderAnimals - duplicates in the input")
    void findOlderAnimals_hasDuplicates() {
        List<Animal> animals = new ArrayList<>(constantsCatSharkDogWolf);
        animals.add(new Shark(Constants.SHARK));

        Map<Animal, Integer> expected = new HashMap<>();
        expected.put(Constants.SHARK, Constants.SHARK.getAgeYears());

        assertEquals(
                expected,
                animalsRepositoryImpl.findOlderAnimals(animals, 20)
        );
    }

    @Test
    @DisplayName("AnimalsRepositoryImpl.findOlderAnimals - normal")
    void findOlderAnimals() {
        Map<Animal, Integer> expected = new HashMap<>();
        expected.put(Constants.CAT, Constants.CAT.getAgeYears());
        expected.put(Constants.SHARK, Constants.SHARK.getAgeYears());

        assertEquals(
                expected,
                animalsRepositoryImpl.findOlderAnimals(constantsCatSharkDogWolf, 10)
        );
    }

    @ParameterizedTest(name = "List<Animal> is {0}")
    @NullSource
    @DisplayName("AnimalsRepositoryImpl.findAllDuplicates - list is null")
    void findAllDuplicates_null(List<Animal> animals) {
        assertEquals(
                Constants.EMPTY_MAP_STRING,
                animalsRepositoryImpl.findAllDuplicates(animals).toString()
        );
        assertEquals(Constants.EMPTY_STRING, outputStreamCaptor.toString().trim());
    }

    @Test
    @DisplayName("AnimalsRepositoryImpl.findAllDuplicates - list is empty")
    void findAllDuplicates_empty() {
        assertEquals(
                Constants.EMPTY_MAP_STRING,
                animalsRepositoryImpl.findAllDuplicates(new ArrayList<>()).toString()
        );
        assertEquals(Constants.EMPTY_STRING, outputStreamCaptor.toString().trim());
    }

    @Test
    @DisplayName("AnimalsRepositoryImpl.findAllDuplicates - no duplicates")
    void findAllDuplicates_noDuplicates() {
        assertEquals(
                Constants.EMPTY_MAP_STRING,
                animalsRepositoryImpl.findAllDuplicates(constantsCatSharkDogWolf).toString()
        );
    }

    @Test
    @DisplayName("AnimalsRepositoryImpl.findAllDuplicates - normal")
    void findAllDuplicates() {
        Animal shark = new Shark(Constants.SHARK);

        ArrayList<Animal> animals = new ArrayList<>(constantsCatSharkDogWolf);
        animals.add(shark);

        assertEquals(
                "{Shark=[{\"type\":\"Shark\",\"breed\":\"Bull shark\",\"character\":\"sentimental\",\"name\":\"Daisy\",\"birthdate\":\"20-04-1991\"}, " +
                        "{\"type\":\"Shark\",\"breed\":\"Bull shark\",\"character\":\"sentimental\",\"name\":\"Daisy\",\"birthdate\":\"20-04-1991\"}]}",
                animalsRepositoryImpl.findAllDuplicates(animals).toString()
        );
        assertEquals(shark + System.lineSeparator() + shark, outputStreamCaptor.toString().trim());
    }
}