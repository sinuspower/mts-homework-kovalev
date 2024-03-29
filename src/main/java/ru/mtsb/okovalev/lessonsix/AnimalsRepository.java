package ru.mtsb.okovalev.lessonsix;

import ru.mtsb.okovalev.lessonthree.animals.Animal;

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
     * содержащее во входящем массиве.
     *
     * @param animals       Массив животных
     * @param ageYearsBound Возраст, строго старше которого должны быть все члены результирующей карты
     * @return Map; ключ - объект Animal, значение - возраст
     */
    Map<Animal, Integer> findOlderAnimals(List<Animal> animals, int ageYearsBound);

    /**
     * Выводит на экран дубликаты животных и возвращает количества найденных дубликатов для каждого
     * типа животного из входящего массива. Находит все дубликаты, то есть для двух одинаковых животных
     * в исходном массиве выводит на экран их оба, а в соответствующее количество в результирующем
     * ассоциативном массиве записывает число 2.
     *
     * @param animals Массив животных
     * @return Map; ключ - тип животного, значение - количество повторяющихся животных данного типа
     */
    Map<String, Integer> findAllDuplicates(List<Animal> animals);
}
