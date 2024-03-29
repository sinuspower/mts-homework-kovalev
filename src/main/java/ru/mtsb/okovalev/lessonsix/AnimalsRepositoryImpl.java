package ru.mtsb.okovalev.lessonsix;

import ru.mtsb.okovalev.lessonthree.animals.Animal;
import ru.mtsb.okovalev.lessonthree.animals.Dog;

import java.time.LocalDate;
import java.util.*;

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
        if (Objects.isNull(animals) || animals.isEmpty()) {
            return new HashMap<>();
        }

        Map<String, List<LocalDate>> result = new HashMap<>();

        LocalDate birthdate;
        ArrayList<LocalDate> birthdates;
        String key;
        for (Animal animal : animals) {
            birthdate = animal.getBirthdate();
            if (birthdate.isLeapYear()) {
                key = animal.getType() + " " + animal.getName();
                if (result.containsKey(key)) {
                    result.get(key).add(birthdate);
                } else {
                    birthdates = new ArrayList<>();
                    birthdates.add(birthdate);
                    result.put(key, birthdates);
                }
            }
        }

        return result;
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

        int ageYears;
        Animal oldest = new Dog(); // birthdate is null => oldest.getAgeYears() = 0
        for (Animal animal : animals) {
            ageYears = animal.getAgeYears();
            if (ageYears > ageYearsBound) {
                result.put(animal, ageYears);
            }
            if (oldest.getAgeYears() < ageYears) {
                oldest = animal;
            }
        }

        if (result.isEmpty()) {
            result.put(oldest, oldest.getAgeYears());
        }

        return result;
    }

    /**
     * Выводит на экран дубликаты животных и возвращает количества найденных дубликатов для каждого
     * типа животного из входящего массива. Находит все дубликаты, то есть для двух одинаковых животных
     * в исходном массиве выводит на экран их оба, а в соответствующее количество в результирующем
     * ассоциативном массиве записывает число 2.
     *
     * @param animals Массив животных
     * @return Map; ключ - тип животного, значение - количество повторяющихся животных данного типа
     */
    @Override
    public Map<String, Integer> findAllDuplicates(List<Animal> animals) {
        if (Objects.isNull(animals) || animals.isEmpty()) {
            return new HashMap<>();
        }

        // save repetition counts of unique animals in additional map
        Map<Animal, Integer> counts = new HashMap<>();
        for (Animal animal : animals) {
            addCount(counts, animal, 1);
        }

        // print all duplicates in the same order as in the source array
        for (Animal animal : animals) {
            if (counts.get(animal) > 1) {
                System.out.println(animal);
            }
        }

        // get repetition counts for types of duplicated animals
        Map<String, Integer> result = new HashMap<>();
        Iterator<Map.Entry<Animal, Integer>> iterator = counts.entrySet().iterator();
        Animal animal;
        int count;
        while (iterator.hasNext()) {
            animal = iterator.next().getKey();
            count = counts.get(animal);
            if (count > 1) {
                addCount(result, animal.getType().toString(), count);
            }
        }

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
