package ru.mtsb.okovalev.lessonthree.animals;

/**
 * Интерфейс содержит методы, описывающие поведение животного.
 */
public interface Animal {
    /**
     * Движение.
     */
    void move();

    /**
     * Питание.
     */
    void eat();

    /**
     * Издаваемый звук.
     */
    void sound();

    /**
     * Образ жизни.
     */
    void live();
}
