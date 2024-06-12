package ru.mtsb.okovalev.lessonnine;

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
    private static final int SECRET_INFO_FILE_LINE_LENGTH = 24;

    public static void main(String[] args) {
        var createAnimalsServiceImpl = new CreateAnimalsServiceImpl();
        int n = 4;

        try {
            createSecretInformationFile();

            ArrayList<Animal> animals = createAnimalsServiceImpl.create(n);
            System.out.println("\t" + n + " animals created by CreateAnimalsServiceImpl.create(" + n + ")");
            System.out.println(Representations.asJson_ListAnimal(animals) + "\n");
        } catch (IOException e) {
            System.out.println("Can not write file: " + e.getMessage());
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
