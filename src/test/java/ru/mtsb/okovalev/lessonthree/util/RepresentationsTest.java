package ru.mtsb.okovalev.lessonthree.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.NullSource;
import ru.mtsb.okovalev.lessonthree.animals.Animal;
import ru.mtsb.okovalev.lessonthree.util.providers.AnimalsArrayListArgumentsProvider;
import ru.mtsb.okovalev.lessonthree.util.providers.AnimalsMapArgumentsProvider;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RepresentationsTest {
    @ParameterizedTest(name = "ArrayList<Animal> is {0}")
    @NullSource
    @DisplayName("Representations.asJson_ArrayListAnimal - list is null")
    void asJson_arrayList_null(ArrayList<Animal> animals) {
        assertEquals("[]", Representations.asJson_ArrayListAnimal(animals));
    }

    @Test
    @DisplayName("Representations.asJson_ArrayListAnimal - list is empty")
    void asJson_arrayList_empty() {
        assertEquals("[]", Representations.asJson_ArrayListAnimal(new ArrayList<>()));
    }

    @ParameterizedTest(name = "Animal is {0}")
    @NullSource
    @DisplayName("Representations.asJson_ArrayListAnimal - animal is null")
    void asJson_arrayList_nullAnimal(Animal animal) {
        ArrayList<Animal> animals = new ArrayList<>();
        animals.add(Constants.SHARK);
        animals.add(animal);
        animals.add(Constants.CAT);

        assertEquals("[\n" +
                "\t{\"type\":\"Shark\",\"breed\":\"Bull shark\",\"character\":\"sentimental\",\"name\":\"Daisy\",\"birthdate\":\"" +
                LocalDate.of(1991, 4, 20).format(DateTimeFormatter.ofPattern(Constants.SHARK.getBirthdateFormat())) + "\"},\n" +
                "null,\n" +
                "\t{\"type\":\"Cat\",\"breed\":\"Javanese\",\"character\":\"phlegmatic\",\"name\":\"Bambi\",\"birthdate\":\"" +
                LocalDate.of(2011, 12, 26).format(DateTimeFormatter.ofPattern(Constants.CAT.getBirthdateFormat())) + "\",\"cost\":\"$1200,00\"}\n" +
                "]", Representations.asJson_ArrayListAnimal(animals));
    }

    @ParameterizedTest(name = "Map<String, List<Animal>> is {0}")
    @NullSource
    @DisplayName("Representations.asJson_MapStringListAnimal - map is null")
    void asJson_map_null(Map<String, List<Animal>> animals) {
        assertEquals("{}", Representations.asJson_MapStringListAnimal(animals));
    }

    @Test
    @DisplayName("Representations.asJson_MapStringListAnimal - map is empty")
    void asJson_map_empty() {
        assertEquals("{}", Representations.asJson_MapAnimalInteger(new HashMap<>()));
    }

    @ParameterizedTest(name = "{0}")
    @ArgumentsSource(AnimalsArrayListArgumentsProvider.class)
    @DisplayName("Representations.asJson_ArrayListAnimal - normal lists")
    void asJson_arrayList(ArrayList<Animal> animals, String expected) {
        assertEquals(expected, Representations.asJson_ArrayListAnimal(animals));
    }

    @ParameterizedTest(name = "{0}")
    @ArgumentsSource(AnimalsMapArgumentsProvider.class)
    @DisplayName("Representations.asJson_MapStringListAnimal - normal maps")
    void asJson_map(Map<String, List<Animal>> animals, String expected) {
        assertEquals(expected, Representations.asJson_MapStringListAnimal(animals));
    }
}