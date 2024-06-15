package ru.mtsb.okovalev.lessonsix;

import ru.mtsb.okovalev.lessonthree.animals.Animal;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * Интерфейс содержит статистические методы для работы с животными.
 */
public interface AnimalsRepository {
    /**
     * Возвращает всех животных, которые родились в високосный год.
     *
     * @param animals Массив животных
     * @return Map; ключ - "&lt;Тип животного&gt; &lt;Кличка&gt;",
     * значение - список дат рождения найденных одноимённых животных
     */
    Map<String, List<LocalDate>> findLeapYearNames(List<Animal> animals);

    /**
     * Возвращает всех животных, которые строго старше заданного возраста в годах.
     * Если не найдено ни одного такого животного, то результат - самое старшее животное,
     * содержащее во входящем массиве. Результат своей работы метод дополнительно
     * записывает в файл в формате JSON.
     *
     * @param animals       Массив животных
     * @param ageYearsBound Возраст, строго старше которого должны быть все члены результирующей карты
     * @return Map; ключ - объект Animal, значение - возраст
     * @throws IOException если произошло исключение во время записи результирующего файла
     */
    Map<Animal, Integer> findOlderAnimals(List<Animal> animals, int ageYearsBound) throws IOException;

    /**
     * Выводит на экран дубликаты животных и возвращает списки найденных дубликатов для каждого
     * типа животного из входящего массива. Находит все дубликаты, то есть для двух одинаковых животных
     * в исходном массиве выводит на экран и добавляет в результирующий список дубликатов их обоих.
     *
     * @param animals Массив животных
     * @return Map; ключ - тип животного, значение - список дубликатов
     */
    Map<String, List<Animal>> findAllDuplicates(List<Animal> animals);

    /**
     * Вычисляет средний возраст в годах для животных из заданного списка.
     * Результат выводит на экран в формате "Average age in years: &lt;Средний возраст&gt;"
     *
     * @param animals Заданный список животных
     */
    void findAverageAge(List<Animal> animals);

    /**
     * Возвращает список животных, возраст которых больше пяти лет, а стоимость -
     * больше средней по исходному списку.
     *
     * @param animals Список животных
     * @return список животных, отсортированный по возрастанию даты рождения
     */
    List<Animal> findOldAndExpensive(List<Animal> animals);

    /**
     * Возвращает список имён не более трёх животных с самой низкой ценой.
     *
     * @param animals Список животных
     * @return список имён, отсортированный в обратном порядке
     */
    List<String> findMinCostAnimals(List<Animal> animals);
}
