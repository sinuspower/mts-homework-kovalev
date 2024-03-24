package ru.mtsb.okovalev.lessonthree.animals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.ValueSource;
import ru.mtsb.okovalev.lessonthree.AnimalsFactory;

import java.time.LocalDate;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class AnimalTest {
    private final AnimalsFactory animalsFactory = new AnimalsFactory();

    static class GetAgeYearsArgumentsProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                    Arguments.of(LocalDate.of(1991, 4, 20), 32),
                    Arguments.of(LocalDate.of(2001, 3, 11), 23),
                    Arguments.of(LocalDate.of(2024, 3, 11), 0),
                    Arguments.of(LocalDate.of(1924, 3, 24), 100)
            );
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"2023-02-11", "1992-11-19", "2005-12-10"})
    @DisplayName("setBirthdate - ok")
    void setBirthdate_ok(String birthdate) {
        Animal wolf = new Wolf();

        assertNull(wolf.getBirthdate());
        assertDoesNotThrow(() -> wolf.setBirthdate(LocalDate.parse(birthdate)));

        wolf.setBirthdateFormat("yyyy-MM-dd");
        assertEquals(birthdate, wolf.getBirthdateFormatted());
    }

    @Test
    @DisplayName("setBirthdate - exception")
    void setBirthdate_exception() {
        Animal dog = new Dog();
        assertNull(dog.getBirthdate());

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class,
                () -> dog.setBirthdate(LocalDate.now().plusDays(1)));

        assertEquals("Tried to set birthdate greater than today", thrown.getMessage());
    }

    @ParameterizedTest
    @ArgumentsSource(GetAgeYearsArgumentsProvider.class)
    void getAgeYears(LocalDate birthdate, int ageYears) {
        Animal animal = animalsFactory.getRandomAnimal();
        assertDoesNotThrow(() -> animal.setBirthdate(birthdate));
        assertEquals(ageYears, animal.getAgeYears());
    }

    @Test
    @DisplayName("getAgeYears - birthdate is null")
    void getAgeYears_birthdateIsNull() {
        assertEquals(0, new Dog().getAgeYears());
    }
}