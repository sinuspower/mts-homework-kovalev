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
     * Если не найдено ни одного такого животного, то результат - первое самое старшее
     * животное, содержащееся во входящем массиве, и его возраст.
     *
     * @param animals       Массив животных
     * @param ageYearsBound Возраст, строго старше которого должны быть все члены результирующей карты
     * @return Map; ключ - объект Animal, значение - возраст
     */
    @Override
    public Map<Animal, Integer> findOlderAnimals(List<Animal> animals, int ageYearsBound) {
        if (Objects.isNull(animals) || animals.isEmpty()) {
            return new HashMap<>();
        }

        Map<Animal, Integer> result = new HashMap<>();

        // Stream API

        return result;
    }

    /**
     * Выводит на экран дубликаты животных и возвращает списки найденных дубликатов для каждого
     * типа животного из входящего массива. Находит все дубликаты, то есть для двух одинаковых животных
     * в исходном массиве выводит на экран и добавляет в результирующий список дубликатов их обоих.
     *
     * @param animals Массив животных
     * @return Map; ключ - тип животного, значение - список дубликатов
     */
    @Override
    public Map<String, List<Animal>> findAllDuplicates(List<Animal> animals) {
        if (Objects.isNull(animals) || animals.isEmpty()) {
            return new HashMap<>();
        }

        Map<String, List<Animal>> result = new HashMap<>();

        // Stream API

        return result;
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

        double averageAge = 0;

        // Stream API

        System.out.println("Average age in years: " + averageAge);
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

        ArrayList<Animal> result = new ArrayList<>();

        // Stream API

        return result;
    }

    /**
     * Возвращает список имён не более трёх животных с самой низкой ценой.
     *
     * @param animals Список животных
     * @return Список имён, отсортированный в обратном порядке
     */
    public List<String> findMinConstAnimals(List<Animal> animals) {
        if (Objects.isNull(animals) || animals.isEmpty()) {
            return new ArrayList<>();
        }

        ArrayList<String> result = new ArrayList<>();

        // Stream API

        return result;
    }

    private <T> void addCount(Map<T, Integer> map, T key, Integer count) {
        if (map.containsKey(key)) {
            map.put(key, map.get(key) + count);
        } else {
            map.put(key, count);
        }
    }
}
