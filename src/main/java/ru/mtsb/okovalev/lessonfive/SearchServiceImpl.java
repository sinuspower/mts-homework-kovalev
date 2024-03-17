package ru.mtsb.okovalev.lessonfive;

import ru.mtsb.okovalev.lessonfive.exceptions.InvalidAnimalBirthdateException;
import ru.mtsb.okovalev.lessonfive.exceptions.InvalidAnimalException;
import ru.mtsb.okovalev.lessonthree.animals.Animal;

import java.time.LocalDate;

public class SearchServiceImpl implements SearchService {
    /**
     * Проверяет, родилось ли животное в високосный год.
     * Результат проверки выводит в стандартный поток вывода. Формат вывода:
     * "{animal.getName()} was born on a leap year"
     * или
     * "{animal.getName()} was not born on a leap year"
     *
     * @param animal Любая имплементация интерфейса Animal
     * @throws InvalidAnimalException          если параметр animal == null
     * @throws InvalidAnimalBirthdateException если animal.getBirthdate() возвращает null
     */
    @Override
    public void checkLeapYearAnimal(Animal animal) throws InvalidAnimalException, InvalidAnimalBirthdateException {
        if (animal == null) {
            throw new InvalidAnimalException("Incorrect animal has been passed for check at " + LocalDate.now());
        }

        if (animal.getBirthdate() == null) {
            throw new InvalidAnimalBirthdateException(animal.getType() + " passed for check has not birthdate filled");
        }

        System.out.println(animal.getBirthdate().isLeapYear() ?
                animal.getName() + " was born on a leap year" :
                animal.getName() + " was not born on a leap year"
        );
    }
}
