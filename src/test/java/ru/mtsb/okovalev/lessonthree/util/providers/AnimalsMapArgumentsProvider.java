package ru.mtsb.okovalev.lessonthree.util.providers;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import ru.mtsb.okovalev.lessonthree.animals.*;
import ru.mtsb.okovalev.lessonthree.animals.enums.AnimalType;
import ru.mtsb.okovalev.lessonthree.util.Constants;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class AnimalsMapArgumentsProvider implements ArgumentsProvider {
    private final Dog dog = Constants.DOG;
    private final Cat cat = Constants.CAT;
    private final Shark shark = Constants.SHARK;
    private final Wolf wolf = Constants.WOLF;

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        Map<String, List<Animal>> mapWithNull = new LinkedHashMap<>();
        String mapWithNullExpectedJson = initWithNull(mapWithNull);

        Map<String, List<Animal>> mapWithEmpty = new LinkedHashMap<>();
        String mapWithEmptyExpectedJson = initWithEmpty(mapWithEmpty);

        Map<String, List<Animal>> mapWithNullAndEmpty = new LinkedHashMap<>();
        String mapWithNullAndEmptyExpectedJson = initWithNullAndEmpty(mapWithNullAndEmpty);

        Map<String, List<Animal>> mapNormal = new LinkedHashMap<>();
        String mapNormalExpectedJson = initNormal(mapNormal);

        return Stream.of(
                Arguments.of(mapWithNull, mapWithNullExpectedJson),
                Arguments.of(mapWithEmpty, mapWithEmptyExpectedJson),
                Arguments.of(mapWithNullAndEmpty, mapWithNullAndEmptyExpectedJson),
                Arguments.of(mapNormal, mapNormalExpectedJson)
        );
    }

    private String initWithNull(Map<String, List<Animal>> map) {
        ArrayList<Animal> animalsList = new ArrayList<>();
        animalsList.add(dog);

        map.put(dog.getType().toString(), animalsList);
        map.put(AnimalType.CAT.toString(), null);

        return "{\n" +
                "\t\"Dog\": [\n" +
                "\t\t{\"type\":\"Dog\",\"breed\":\"Affenpinscher\",\"character\":\"strong\",\"name\":\"Cuddles\",\"birthdate\":\"" +
                LocalDate.now().format(DateTimeFormatter.ofPattern(dog.getBirthdateFormat())) + "\",\"cost\":\"$300,00\"}\n" +
                "\t],\n" +
                "\t\"Cat\": null\n" +
                "}";
    }

    private String initWithEmpty(Map<String, List<Animal>> map) {
        ArrayList<Animal> animalsList = new ArrayList<>();
        map.put(AnimalType.SHARK.toString(), animalsList);

        animalsList = new ArrayList<>();
        animalsList.add(wolf);
        map.put(wolf.getType().toString(), animalsList);

        return "{\n" +
                "\t\"Shark\": [],\n" +
                "\t\"Wolf\": [\n" +
                "\t\t{\"type\":\"Wolf\",\"breed\":\"Mexican wolf\",\"character\":\"phlegmatic\",\"name\":\"Cesar\",\"birthdate\":\"" +
                LocalDate.now().format(DateTimeFormatter.ofPattern(wolf.getBirthdateFormat())) + "\"}\n" +
                "\t]\n" +
                "}";
    }

    private String initWithNullAndEmpty(Map<String, List<Animal>> map) {
        ArrayList<Animal> animalsList = new ArrayList<>();
        animalsList.add(cat);
        map.put(cat.getType().toString(), animalsList);

        map.put(AnimalType.SHARK.toString(), null);

        animalsList = new ArrayList<>();
        animalsList.add(dog);
        map.put(dog.getType().toString(), animalsList);

        map.put(AnimalType.WOLF.toString(), new ArrayList<>());

        return "{\n" +
                "\t\"Cat\": [\n" +
                "\t\t{\"type\":\"Cat\",\"breed\":\"Javanese\",\"character\":\"phlegmatic\",\"name\":\"Bambi\",\"birthdate\":\"" +
                LocalDate.of(2011, 12, 26).format(DateTimeFormatter.ofPattern(cat.getBirthdateFormat())) + "\",\"cost\":\"$1200,00\"}\n" +
                "\t],\n" +
                "\t\"Shark\": null,\n" +
                "\t\"Dog\": [\n" +
                "\t\t{\"type\":\"Dog\",\"breed\":\"Affenpinscher\",\"character\":\"strong\",\"name\":\"Cuddles\",\"birthdate\":\"" +
                LocalDate.now().format(DateTimeFormatter.ofPattern(dog.getBirthdateFormat())) + "\",\"cost\":\"$300,00\"}\n" +
                "\t],\n" +
                "\t\"Wolf\": []\n" +
                "}";
    }

    private String initNormal(Map<String, List<Animal>> map) {
        ArrayList<Animal> animalsList = new ArrayList<>();
        animalsList.add(cat);
        map.put(cat.getType().toString(), animalsList);

        animalsList = new ArrayList<>();
        animalsList.add(shark);
        map.put(shark.getType().toString(), animalsList);

        animalsList = new ArrayList<>();
        animalsList.add(dog);
        map.put(dog.getType().toString(), animalsList);

        animalsList = new ArrayList<>();
        animalsList.add(wolf);
        map.put(wolf.getType().toString(), animalsList);

        return "{\n" +
                "\t\"Cat\": [\n" +
                "\t\t{\"type\":\"Cat\",\"breed\":\"Javanese\",\"character\":\"phlegmatic\",\"name\":\"Bambi\",\"birthdate\":\"" +
                LocalDate.of(2011, 12, 26).format(DateTimeFormatter.ofPattern(cat.getBirthdateFormat())) + "\",\"cost\":\"$1200,00\"}\n" +
                "\t],\n" +
                "\t\"Shark\": [\n" +
                "\t\t{\"type\":\"Shark\",\"breed\":\"Bull shark\",\"character\":\"sentimental\",\"name\":\"Daisy\",\"birthdate\":\"" +
                LocalDate.of(1991, 4, 20).format(DateTimeFormatter.ofPattern(shark.getBirthdateFormat())) + "\"}\n" +
                "\t],\n" +
                "\t\"Dog\": [\n" +
                "\t\t{\"type\":\"Dog\",\"breed\":\"Affenpinscher\",\"character\":\"strong\",\"name\":\"Cuddles\",\"birthdate\":\"" +
                LocalDate.now().format(DateTimeFormatter.ofPattern(dog.getBirthdateFormat())) + "\",\"cost\":\"$300,00\"}\n" +
                "\t],\n" +
                "\t\"Wolf\": [\n" +
                "\t\t{\"type\":\"Wolf\",\"breed\":\"Mexican wolf\",\"character\":\"phlegmatic\",\"name\":\"Cesar\",\"birthdate\":\"" +
                LocalDate.now().format(DateTimeFormatter.ofPattern(wolf.getBirthdateFormat())) + "\"}\n" +
                "\t]\n" +
                "}";
    }
}
