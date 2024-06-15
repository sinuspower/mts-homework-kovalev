package ru.mtsb.okovalev.lessonsix;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.NullSource;
import ru.mtsb.okovalev.Constants;
import ru.mtsb.okovalev.lessonthree.animals.Animal;
import ru.mtsb.okovalev.lessonthree.animals.Cat;
import ru.mtsb.okovalev.lessonthree.animals.Shark;
import ru.mtsb.okovalev.lessonthree.animals.enums.AnimalCharacter;
import ru.mtsb.okovalev.lessonthree.animals.enums.AnimalName;
import ru.mtsb.okovalev.lessonthree.animals.enums.CatBreed;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AnimalsRepositoryImplTest {
    private final AnimalsRepository animalsRepositoryImpl = new AnimalsRepositoryImpl();
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final List<Animal> constantsCatSharkDogWolf = List.of(
            Constants.CAT, Constants.SHARK, Constants.DOG, Constants.WOLF);

    private static class FindAverageAgeArgumentsProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {
            ArrayList<Animal> oneAnimal = new ArrayList<>();
            oneAnimal.add(Constants.DOG);

            ArrayList<Animal> twoAnimals = new ArrayList<>();
            twoAnimals.add(Constants.DOG);
            twoAnimals.add(Constants.CAT);

            ArrayList<Animal> threeAnimals = new ArrayList<>();
            threeAnimals.add(Constants.DOG);
            threeAnimals.add(Constants.CAT);
            threeAnimals.add(Constants.SHARK);

            ArrayList<Animal> fourAnimals = new ArrayList<>();
            fourAnimals.add(Constants.DOG);
            fourAnimals.add(Constants.CAT);
            fourAnimals.add(Constants.SHARK);
            fourAnimals.add(Constants.WOLF);

            return Stream.of(
                    Arguments.of(oneAnimal, (double) Constants.DOG.getAgeYears()),
                    Arguments.of(twoAnimals, averageAgeYears(twoAnimals)),
                    Arguments.of(threeAnimals, averageAgeYears(threeAnimals)),
                    Arguments.of(fourAnimals, averageAgeYears(fourAnimals))
            );
        }

        private double averageAgeYears(List<Animal> animals) {
            return animals
                    .stream()
                    .mapToDouble(Animal::getAgeYears)
                    .average()
                    .orElse(0);
        }
    }

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
    void findOlderAnimals_null(List<Animal> animals) throws IOException {
        assertEquals(
                Constants.EMPTY_MAP_STRING,
                animalsRepositoryImpl.findOlderAnimals(animals, 18).toString()
        );
    }

    @Test
    @DisplayName("AnimalsRepositoryImpl.findOlderAnimals - list is empty")
    void findOlderAnimals_empty() throws IOException {
        assertEquals(
                Constants.EMPTY_MAP_STRING,
                animalsRepositoryImpl.findOlderAnimals(new ArrayList<>(), 18).toString()
        );
    }

    @Test
    @DisplayName("AnimalsRepositoryImpl.findOlderAnimals - no one is older")
    void findOlderAnimals_noOlder() throws IOException {
        assertEquals(
                "{{\"type\":\"Shark\",\"breed\":\"Bull shark\",\"character\":\"sentimental\",\"name\":\"Daisy\",\"birthdate\":\"20-04-1991\"}=33}",
                animalsRepositoryImpl.findOlderAnimals(constantsCatSharkDogWolf, 40).toString()
        );
    }

    @Test
    @DisplayName("AnimalsRepositoryImpl.findOlderAnimals - duplicates in the input")
    void findOlderAnimals_hasDuplicates() throws IOException {
        List<Animal> animals = new ArrayList<>(constantsCatSharkDogWolf);
        animals.add(new Shark(Constants.SHARK));

        Map<Animal, Integer> expected = new HashMap<>();
        expected.put(Constants.SHARK, Constants.SHARK.getAgeYears());
        expected.put(new Shark(Constants.SHARK), Constants.SHARK.getAgeYears());

        assertEquals(
                expected.toString(),
                animalsRepositoryImpl.findOlderAnimals(animals, 20).toString()
        );
    }

    @Test
    @DisplayName("AnimalsRepositoryImpl.findOlderAnimals - normal")
    void findOlderAnimals() throws IOException {
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

    @ParameterizedTest(name = "List<Animal> is {0}")
    @NullSource
    @DisplayName("AnimalsRepositoryImpl.findAverageAge - list is null")
    void findAverageAge_null(List<Animal> animals) {
        animalsRepositoryImpl.findAverageAge(animals);
        assertEquals("Average age in years: 0.0", outputStreamCaptor.toString().trim());
    }

    @Test
    @DisplayName("AnimalsRepositoryImpl.findAverageAge - list is empty")
    void findAverageAge_empty() {
        animalsRepositoryImpl.findAverageAge(new ArrayList<>());
        assertEquals("Average age in years: 0.0", outputStreamCaptor.toString().trim());
    }

    @Test
    @DisplayName("AnimalsRepositoryImpl.findAverageAge - one animal in the list")
    void findAverageAge_oneAnimal() {
        ArrayList<Animal> animals = new ArrayList<>();
        animals.add(Constants.WOLF);

        animalsRepositoryImpl.findAverageAge(animals);
        assertEquals("Average age in years: 4.0", outputStreamCaptor.toString().trim());
    }

    @Test
    @DisplayName("AnimalsRepositoryImpl.findAverageAge - duplicates in the input")
    void findAverageAge_hasDuplicates() {
        ArrayList<Animal> animals = new ArrayList<>();
        animals.add(Constants.WOLF);
        animals.add(Constants.WOLF);

        animalsRepositoryImpl.findAverageAge(animals);
        assertEquals("Average age in years: 4.0", outputStreamCaptor.toString().trim());
    }

    @ParameterizedTest(name = "{0}: {1}")
    @ArgumentsSource(FindAverageAgeArgumentsProvider.class)
    @DisplayName("AnimalsRepositoryImpl.findAverageAge - normal")
    void findAverageAge(List<Animal> animals, double averageAgeYearsExpected) {
        animalsRepositoryImpl.findAverageAge(animals);
        assertEquals("Average age in years: " + averageAgeYearsExpected, outputStreamCaptor.toString().trim());
    }

    @ParameterizedTest(name = "List<Animal> is {0}")
    @NullSource
    @DisplayName("AnimalsRepositoryImpl.findOldAndExpensive - list is null")
    void findOldAndExpensive_null(List<Animal> animals) {
        assertEquals("[]", animalsRepositoryImpl.findOldAndExpensive(animals).toString());
    }

    @Test
    @DisplayName("AnimalsRepositoryImpl.findOldAndExpensive - list is empty")
    void findOldAndExpensive_empty() {
        assertEquals("[]", animalsRepositoryImpl.findOldAndExpensive(new ArrayList<>()).toString());
    }

    @Test
    @DisplayName("AnimalsRepositoryImpl.findOldAndExpensive - no results")
    void findOldAndExpensive_noResults() {
        assertEquals("[]", animalsRepositoryImpl.findOldAndExpensive(
                Arrays.asList(Constants.SHARK, Constants.DOG)).toString());
    }

    @Test
    @DisplayName("AnimalsRepositoryImpl.findOldAndExpensive - normal")
    void findOldAndExpensive() {
        ArrayList<Animal> animals = new ArrayList<>(constantsCatSharkDogWolf);
        Animal newCat = new Cat(
                CatBreed.BURMILLA.toString(),
                AnimalCharacter.BLOOD.toString(),
                AnimalName.ANGEL.toString(),
                LocalDate.of(2011, 12, 26),
                800
        );
        animals.add(newCat);

        List<Animal> expected = new ArrayList<>();
        expected.add(Constants.CAT);
        expected.add(newCat);

        assertEquals(
                expected,
                animalsRepositoryImpl.findOldAndExpensive(animals)
        );
    }

    @ParameterizedTest(name = "List<Animal> is {0}")
    @NullSource
    @DisplayName("AnimalsRepositoryImpl.findMinCostAnimals - list is null")
    void findMinCostAnimals_null(List<Animal> animals) {
        assertEquals("[]", animalsRepositoryImpl.findMinCostAnimals(animals).toString());
    }

    @Test
    @DisplayName("AnimalsRepositoryImpl.findMinCostAnimals - list is empty")
    void findMinCostAnimals_empty() {
        assertEquals("[]", animalsRepositoryImpl.findMinCostAnimals(new ArrayList<>()).toString());
    }

    @Test
    @DisplayName("AnimalsRepositoryImpl.findMinCostAnimals - one animal in the list")
    void findMinCostAnimals_oneAnimal() {
        assertEquals(
                List.of(Constants.DOG.getName()),
                animalsRepositoryImpl.findMinCostAnimals(List.of(Constants.DOG)));
    }

    @Test
    @DisplayName("AnimalsRepositoryImpl.findMinCostAnimals - no animals with cost")
    void findMinCostAnimals_noCosts() {
        assertEquals(
                List.of(), // empty list
                animalsRepositoryImpl.findMinCostAnimals(List.of(Constants.SHARK, Constants.WOLF)));
    }

    @Test
    @DisplayName("AnimalsRepositoryImpl.findMinCostAnimals - list of five duplicates with cost")
    void findMinCostAnimals_fiveDuplicates() {
        assertEquals(
                List.of("Bambi", "Bambi", "Bambi"),
                animalsRepositoryImpl.findMinCostAnimals(List.of(Constants.CAT,
                        Constants.CAT, Constants.CAT, Constants.CAT, Constants.CAT)));
    }

    @Test
    @DisplayName("AnimalsRepositoryImpl.findMinCostAnimals - normal")
    void findMinCostAnimals() {
        assertEquals(
                List.of("Cuddles", "Bambi"),
                animalsRepositoryImpl.findMinCostAnimals(constantsCatSharkDogWolf)
        );
    }
}