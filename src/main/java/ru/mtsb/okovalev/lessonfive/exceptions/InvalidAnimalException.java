package ru.mtsb.okovalev.lessonfive.exceptions;

/**
 * Непроверяемое исключение (unchecked exception ~ унаследовано от RuntimeException).
 */
public class InvalidAnimalException extends RuntimeException {
    /**
     * Создаёт объект InvalidAnimalException с указанным описанием исключения.
     *
     * @param message Описание исключения
     */
    public InvalidAnimalException(String message) {
        super(message);
    }
}
