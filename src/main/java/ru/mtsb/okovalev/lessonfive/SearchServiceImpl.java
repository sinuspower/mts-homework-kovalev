package ru.mtsb.okovalev.lessonfive;

import ru.mtsb.okovalev.lessonfive.exceptions.InvalidAnimalBirthdateException;
import ru.mtsb.okovalev.lessonfive.exceptions.InvalidAnimalException;
import ru.mtsb.okovalev.lessonthree.animals.Animal;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Имплементация интерфейса SearchService.
 */
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
    @SuppressWarnings("all")
    public void checkLeapYearAnimal(Animal animal) throws InvalidAnimalException, InvalidAnimalBirthdateException {
        if (Objects.isNull(animal)) {
            throw new InvalidAnimalException("Incorrect animal has been passed for check at " + LocalDate.now());
        }

        if (Objects.isNull(animal.getBirthdate())) {
            throw new InvalidAnimalBirthdateException(animal.getType() + " passed for check has not birthdate filled");
        }

        System.out.println(animal.getName() + (animal.getBirthdate().isLeapYear() ?
                " was born on a leap year" :
                " was not born on a leap year")
        );
    }
}
