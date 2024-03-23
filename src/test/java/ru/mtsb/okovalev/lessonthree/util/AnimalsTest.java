package ru.mtsb.okovalev.lessonthree.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.NullSource;
import ru.mtsb.okovalev.lessonthree.animals.Animal;
import ru.mtsb.okovalev.lessonthree.util.providers.AnimalsMapArgumentsProvider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AnimalsTest {
    @ParameterizedTest(name = "ArrayList<Animal> is {0}")
    @NullSource
    @DisplayName("Animals.asJson : ArrayList<Animal> - list is null")
    void asJson_arrayList_null(ArrayList<Animal> animals) {
        assertEquals("[]", Animals.asJson(animals));
    }

    @Test
    @DisplayName("Animals.asJson : ArrayList<Animal> - list is empty")
    void asJson_arrayList_empty() {
        assertEquals("[]", Animals.asJson(new ArrayList<>()));
    }

    @ParameterizedTest(name = "Map<String, List<Animal>> is {0}")
    @NullSource
    @DisplayName("Animals.asJson : Map<String, List<Animal>> - map is null")
    void asJson_map_null(Map<String, List<Animal>> animals) {
        assertEquals("{}", Animals.asJson(animals));
    }

    @Test
    @DisplayName("Animals.asJson : Map<String, List<Animal>> - map is empty")
    void asJson_map_empty() {
        assertEquals("{}", Animals.asJson(new HashMap<>()));
    }

    @ParameterizedTest(name = "{0}")
    @ArgumentsSource(AnimalsMapArgumentsProvider.class)
    @DisplayName("Animals.asJson : Map<String, List<Animal>> - normal maps")
    void asJson_map(Map<String, List<Animal>> animals, String expected) {
        assertEquals(expected, Animals.asJson(animals));
    }
}