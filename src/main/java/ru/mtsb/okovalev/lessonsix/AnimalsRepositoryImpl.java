package ru.mtsb.okovalev.lessonsix;

import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.core.util.Separators;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import ru.mtsb.okovalev.lessonthree.animals.Animal;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Имплементация интерфейса AnimalsRepository.
 */
public class AnimalsRepositoryImpl implements AnimalsRepository {
    /**
     * Путь по умолчанию к файлу с результатом работы метода {@link #findOlderAnimals(List, int)}.
     */
    public static final Path DEFAULT_FIND_OLDER_ANIMALS_FILE_PATH =
            Path.of("resources/results/findOlderAnimals.json");

    /**
     * ObjectWriter с заданными настройками форматирования результирующего JSON.
     */
    private static final ObjectWriter JSON_OBJECT_WRITER = new ObjectMapper()
            .findAndRegisterModules() // for using of @JsonFormat(pattern = "yyyy-MM-dd") on LocalDate
            .writer(new DefaultPrettyPrinter()
                    .withArrayIndenter(new DefaultIndenter("\t", "\n"))
                    .withObjectIndenter(new DefaultIndenter("\t", "\n"))
                    .withSeparators(new Separators()
                            .withObjectFieldValueSpacing(Separators.Spacing.AFTER)
                            .withArrayValueSpacing(Separators.Spacing.AFTER))
            );

    /**
     * Возвращает всех животных, которые родились в високосный год.
     *
     * @param animals Массив животных
     * @return Map; ключ - "&lt;Тип животного&gt; &lt;Кличка&gt;",
     * значение - список дат рождения найденных одноимённых животных
     */
    @Override
    public Map<String, List<LocalDate>> findLeapYearNames(List<Animal> animals) {
        if (Objects.isNull(animals)) {
            return new HashMap<>();
        }

        return animals
                .stream()
                .filter(a -> a.getBirthdate().isLeapYear())
                .collect(
                        Collectors.groupingBy(
                                a -> a.getType() + " " + a.getName(),
                                Collectors.mapping(
                                        Animal::getBirthdate,
                                        Collectors.toList()
                                )
                        )
                );
    }

    /**
     * Возвращает всех животных, которые строго старше заданного возраста в годах.
     * Если не найдено ни одного такого животного, то результат - самое старшее
     * животное, содержащееся во входящем массиве, и его возраст.
     * Результат своей работы метод дополнительно записывает в файл в формате JSON.
     *
     * @param animals       Массив животных
     * @param ageYearsBound Возраст, строго старше которого должны быть все члены результирующей карты
     * @return Map; ключ - объект Animal, значение - возраст
     * @throws IOException если произошло исключение во время записи результирующего файла
     */
    @Override
    public Map<Animal, Integer> findOlderAnimals(List<Animal> animals, int ageYearsBound) throws IOException {
        if (Objects.isNull(animals)) {
            return new HashMap<>();
        }

        Map<Animal, Integer> result = animals
                .stream()
                .filter(a -> a.getAgeYears() > ageYearsBound)
                .collect(
                        Collectors.toMap(
                                a -> a,
                                Animal::getAgeYears
                        )
                );

        if (result.isEmpty()) {
            animals
                    .stream()
                    .max(Comparator.comparing(Animal::getAgeYears))
                    .ifPresent(a -> result.put(a, a.getAgeYears()));
        }

        writeFindOlderAnimalsJsonFile(result);

        return result;
    }

    /**
     * Записывает результат работы метода {@link #findOlderAnimals(List, int)} -
     * массив объектов Animal - в файл в формате JSON.
     *
     * @param source Объект - источник данных
     * @throws IOException если произошло исключение во время записи файла
     */
    private void writeFindOlderAnimalsJsonFile(Map<Animal, Integer> source) throws IOException {
        Path parent = DEFAULT_FIND_OLDER_ANIMALS_FILE_PATH.getParent();
        if (Files.notExists(parent)) {
            Files.createDirectories(parent);
        }

        var file = new File(DEFAULT_FIND_OLDER_ANIMALS_FILE_PATH.toString());
        JSON_OBJECT_WRITER.writeValue(file, source.keySet());
    }

    /**
     * Выводит на экран дубликаты животных и возвращает списки найденных дубликатов для каждого
     * типа животного из входящего массива. Находит все дубликаты, то есть для двух одинаковых животных
     * в исходном массиве выводит на экран и добавляет в результирующий список дубликатов их обоих.
     * <p>
     * Объекты a и b считаются дубликатами, когда a.toString() = b.toString().
     *
     * @param animals Массив животных
     * @return Map; ключ - тип животного, значение - список дубликатов
     */
    @Override
    public Map<String, List<Animal>> findAllDuplicates(List<Animal> animals) {
        if (Objects.isNull(animals)) {
            return new HashMap<>();
        }

        return animals
                .stream()
                .filter(s -> Collections.frequency(animals
                        .stream()
                        .map(Animal::toString)
                        .collect(Collectors.toList()), s.toString()) > 1)
                .peek(System.out::println)
                .collect(
                        Collectors.groupingBy(
                                a -> a.getType().toString(),
                                Collectors.toList()
                        )
                );
    }

    /**
     * Вычисляет средний возраст в годах для животных из заданного списка.
     * Результат выводит на экран в формате "Average age in years: &lt;Средний возраст&gt;"
     *
     * @param animals Заданный список животных
     */
    public void findAverageAge(List<Animal> animals) {
        if (Objects.isNull(animals) || animals.isEmpty()) {
            System.out.println("Average age in years: 0.0");
            return;
        }

        System.out.println("Average age in years: " + animals
                .stream()
                .collect(Collectors.averagingDouble(Animal::getAgeYears)));
    }

    /**
     * Возвращает список животных, возраст которых больше пяти лет, а стоимость -
     * больше средней по исходному списку.
     *
     * @param animals Список животных
     * @return список животных, отсортированный по возрастанию даты рождения
     */
    public List<Animal> findOldAndExpensive(List<Animal> animals) {
        if (Objects.isNull(animals) || animals.isEmpty()) {
            return new ArrayList<>();
        }

        double averageCost = animals
                .stream()
                .mapToDouble(Animal::getCost)
                .average()
                .orElse(0.0);

        return animals
                .stream()
                .filter(a -> a.getAgeYears() > 5)
                .filter(a -> a.getCost() > averageCost)
                .sorted(Comparator.comparing(Animal::getBirthdate))
                .collect(Collectors.toList());
    }

    /**
     * Возвращает список имён не более трёх животных с самой низкой ценой.
     * Животные, которые не могут иметь стоимости (наследники Predator), не рассматриваются.
     *
     * @param animals Список животных
     * @return список имён, отсортированный в обратном порядке
     */
    public List<String> findMinCostAnimals(List<Animal> animals) {
        if (Objects.isNull(animals)) {
            return new ArrayList<>();
        }

        return animals
                .stream()
                .filter(a -> a.getCost() > 0.0)
                .sorted(Comparator.comparing(Animal::getCost))
                .limit(3)
                .map(Animal::getName)
                .sorted((a, b) -> -a.compareTo(b))
                .collect(Collectors.toList());
    }
}
