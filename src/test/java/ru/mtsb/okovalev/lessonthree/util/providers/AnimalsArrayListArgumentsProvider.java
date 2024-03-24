package ru.mtsb.okovalev.lessonthree.util.providers;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import ru.mtsb.okovalev.Constants;
import ru.mtsb.okovalev.lessonthree.animals.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.stream.Stream;

public class AnimalsArrayListArgumentsProvider implements ArgumentsProvider {
    private final Dog dog = Constants.DOG;
    private final Cat cat = Constants.CAT;
    private final Shark shark = Constants.SHARK;
    private final Wolf wolf = Constants.WOLF;

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        ArrayList<Animal> oneAnimal = new ArrayList<>();
        String oneAnimalExpectedJson = initOne(oneAnimal);

        ArrayList<Animal> twoAnimals = new ArrayList<>();
        String twoAnimalsExpectedJson = initTwo(twoAnimals);

        ArrayList<Animal> threeAnimals = new ArrayList<>();
        String threeAnimalsExpectedJson = initThree(threeAnimals);

        ArrayList<Animal> fourAnimals = new ArrayList<>();
        String fourAnimalsExpectedJson = initFour(fourAnimals);

        return Stream.of(
                Arguments.of(oneAnimal, oneAnimalExpectedJson),
                Arguments.of(twoAnimals, twoAnimalsExpectedJson),
                Arguments.of(threeAnimals, threeAnimalsExpectedJson),
                Arguments.of(fourAnimals, fourAnimalsExpectedJson)
        );
    }

    private String initOne(ArrayList<Animal> animals) {
        animals.add(dog);

        return "[\n" +
                "\t{\"type\":\"Dog\",\"breed\":\"Affenpinscher\",\"character\":\"strong\",\"name\":\"Cuddles\",\"birthdate\":\"" +
                LocalDate.now().format(DateTimeFormatter.ofPattern(dog.getBirthdateFormat())) + "\",\"cost\":\"$300,00\"}\n" +
                "]";
    }

    private String initTwo(ArrayList<Animal> animals) {
        animals.add(dog);
        animals.add(cat);

        return "[\n" +
                "\t{\"type\":\"Dog\",\"breed\":\"Affenpinscher\",\"character\":\"strong\",\"name\":\"Cuddles\",\"birthdate\":\"" +
                LocalDate.now().format(DateTimeFormatter.ofPattern(dog.getBirthdateFormat())) + "\",\"cost\":\"$300,00\"},\n" +
                "\t{\"type\":\"Cat\",\"breed\":\"Javanese\",\"character\":\"phlegmatic\",\"name\":\"Bambi\",\"birthdate\":\"" +
                LocalDate.of(2011, 12, 26).format(DateTimeFormatter.ofPattern(cat.getBirthdateFormat())) + "\",\"cost\":\"$1200,00\"}\n" +
                "]";
    }

    private String initThree(ArrayList<Animal> animals) {
        animals.add(dog);
        animals.add(cat);
        animals.add(shark);

        return "[\n" +
                "\t{\"type\":\"Dog\",\"breed\":\"Affenpinscher\",\"character\":\"strong\",\"name\":\"Cuddles\",\"birthdate\":\"" +
                LocalDate.now().format(DateTimeFormatter.ofPattern(dog.getBirthdateFormat())) + "\",\"cost\":\"$300,00\"},\n" +
                "\t{\"type\":\"Cat\",\"breed\":\"Javanese\",\"character\":\"phlegmatic\",\"name\":\"Bambi\",\"birthdate\":\"" +
                LocalDate.of(2011, 12, 26).format(DateTimeFormatter.ofPattern(cat.getBirthdateFormat())) + "\",\"cost\":\"$1200,00\"},\n" +
                "\t{\"type\":\"Shark\",\"breed\":\"Bull shark\",\"character\":\"sentimental\",\"name\":\"Daisy\",\"birthdate\":\"" +
                LocalDate.of(1991, 4, 20).format(DateTimeFormatter.ofPattern(shark.getBirthdateFormat())) + "\"}\n" +
                "]";
    }

    private String initFour(ArrayList<Animal> animals) {
        animals.add(dog);
        animals.add(cat);
        animals.add(shark);
        animals.add(wolf);

        return "[\n" +
                "\t{\"type\":\"Dog\",\"breed\":\"Affenpinscher\",\"character\":\"strong\",\"name\":\"Cuddles\",\"birthdate\":\"" +
                LocalDate.now().format(DateTimeFormatter.ofPattern(dog.getBirthdateFormat())) + "\",\"cost\":\"$300,00\"},\n" +
                "\t{\"type\":\"Cat\",\"breed\":\"Javanese\",\"character\":\"phlegmatic\",\"name\":\"Bambi\",\"birthdate\":\"" +
                LocalDate.of(2011, 12, 26).format(DateTimeFormatter.ofPattern(cat.getBirthdateFormat())) + "\",\"cost\":\"$1200,00\"},\n" +
                "\t{\"type\":\"Shark\",\"breed\":\"Bull shark\",\"character\":\"sentimental\",\"name\":\"Daisy\",\"birthdate\":\"" +
                LocalDate.of(1991, 4, 20).format(DateTimeFormatter.ofPattern(shark.getBirthdateFormat())) + "\"},\n" +
                "\t{\"type\":\"Wolf\",\"breed\":\"Mexican wolf\",\"character\":\"phlegmatic\",\"name\":\"Cesar\",\"birthdate\":\"" +
                LocalDate.now().format(DateTimeFormatter.ofPattern(wolf.getBirthdateFormat())) + "\"}\n" +
                "]";
    }
}
