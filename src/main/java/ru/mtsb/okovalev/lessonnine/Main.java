package ru.mtsb.okovalev.lessonnine;

import ru.mtsb.okovalev.lessonnine.util.ResultReader;
import ru.mtsb.okovalev.lessonsix.AnimalsRepositoryImpl;
import ru.mtsb.okovalev.lessonthree.CreateAnimalsServiceImpl;
import ru.mtsb.okovalev.lessonthree.animals.AbstractAnimal;
import ru.mtsb.okovalev.lessonthree.animals.Animal;
import ru.mtsb.okovalev.lessonthree.util.Representations;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Random;

public class Main {
    private static final int SECRET_INFO_FILE_LENGTH = 1000;
    private static final int SECRET_INFO_FILE_LINE_LENGTH = 25;

    public static void main(String[] args) {
        var createAnimalsServiceImpl = new CreateAnimalsServiceImpl();
        createAnimalsServiceImpl.setCreateAnimalsLogFilePath(CreateAnimalsServiceImpl.DEFAULT_CREATE_ANIMALS_LOG_FILE_PATH);

        var animalsRepositoryImpl = new AnimalsRepositoryImpl();
        animalsRepositoryImpl.setFindOlderAnimalsJsonFilePath(AnimalsRepositoryImpl.DEFAULT_FIND_OLDER_ANIMALS_JSON_FILE_PATH);

        var resultReader = new ResultReader(
                createAnimalsServiceImpl.getCreateAnimalsLogFilePath(),
                animalsRepositoryImpl.getFindOlderAnimalsJsonFilePath()
        );

        int n = 5;
        String exceptionIn = "";

        try {
            createSecretInformationFile();

            ArrayList<Animal> animals = createAnimalsServiceImpl.create(n);
            System.out.println("\tanimals: " + n + " animals created by CreateAnimalsServiceImpl.create(" + n + ")");
            System.out.println(Representations.asJson_ListAnimal(animals));
            System.out.println("\tLog file appended: " + createAnimalsServiceImpl.getCreateAnimalsLogFilePath());
            System.out.println("\tLog file lines count: " + resultReader.createAnimalsLogFileLinesCount() + "\n");

            int ageYearsBound = 10;
            exceptionIn = "Exception in AnimalsRepositoryImpl.findOlderAnimals";
            System.out.println("\tAnimalsRepositoryImpl.findOlderAnimals(animals, " + ageYearsBound + ")\n\tResult:\n" +
                    Representations.asJson_MapAnimalInteger(animalsRepositoryImpl.findOlderAnimals(animals, ageYearsBound)));
            System.out.println("\tOutput file: " + animalsRepositoryImpl.getFindOlderAnimalsJsonFilePath());

            exceptionIn = "Exception in ResultReader.readFindOlderAnimalsJsonFile";
            System.out.println("\tAnimals from file:\n" +
                    Representations.asJson_ListAnimal(resultReader.readFindOlderAnimalsJsonFile()) + "\n");
        } catch (IOException e) {
            System.out.println(exceptionIn + ": " + e.getMessage());
        }
    }

    private static void createSecretInformationFile() throws IOException {
        Path path = AbstractAnimal.SECRET_INFO_FILE_PATH;
        Path parent = path.getParent();
        if (Files.notExists(parent)) {
            Files.createDirectories(parent);
        }

        Files.deleteIfExists(path);

        for (int i = 0; i < SECRET_INFO_FILE_LENGTH; i++) {
            Files.writeString(path,
                    randomString() + "\n",
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND);
        }
    }

    private static String randomString() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(SECRET_INFO_FILE_LINE_LENGTH)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
