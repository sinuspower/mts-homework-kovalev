package ru.mtsb.okovalev.lessonsix;

import ru.mtsb.okovalev.lessonthree.animals.Animal;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Имплементация интерфейса AnimalsRepository.
 */
public class AnimalsRepositoryImpl implements AnimalsRepository {
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
     *
     * @param animals       Массив животных
     * @param ageYearsBound Возраст, строго старше которого должны быть все члены результирующей карты
     * @return Map; ключ - объект Animal, значение - возраст
     */
    @Override
    public Map<Animal, Integer> findOlderAnimals(List<Animal> animals, int ageYearsBound) {
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

        return result;
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
