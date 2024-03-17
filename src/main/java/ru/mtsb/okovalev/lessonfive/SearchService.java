package ru.mtsb.okovalev.lessonfive;

import ru.mtsb.okovalev.lessonfive.exceptions.InvalidAnimalBirthdateException;
import ru.mtsb.okovalev.lessonfive.exceptions.InvalidAnimalException;
import ru.mtsb.okovalev.lessonthree.animals.Animal;

/**
 * Интерфейс содержит методы проверки, обладают ли животные определёнными свойствами.
 */
public interface SearchService {
    /**
     * Проверяет, родилось ли животное в високосный год.
     * Результат проверки выводит в стандартный поток вывода.
     *
     * @param animal Любая имплементация интерфейса Animal
     */
    void checkLeapYearAnimal(Animal animal) throws InvalidAnimalException, InvalidAnimalBirthdateException;
}
