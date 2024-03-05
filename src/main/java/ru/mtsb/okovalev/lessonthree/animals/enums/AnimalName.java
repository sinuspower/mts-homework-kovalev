package ru.mtsb.okovalev.lessonthree.animals.enums;

/**
 * Перечисление кличек животных.
 */
@SuppressWarnings("unused")
public enum AnimalName {
    FLOWER("Flower"), BAMBI("Bambi"), CUDDLES("Cuddles"), LOLA("Lola"), TWEETY("Tweety"), ANGEL("Angel"), CESAR("Cesar"), DAISY("Daisy"), TUTU("Tutu"), BABY("Baby");

    private final String printable;

    AnimalName(String printable) {
        this.printable = printable;
    }

    /**
     * Возвращает представление клички животного, применимое для человекочитаемого вывода.
     *
     * @return кличка животного в формате для человекочитаемого вывода
     */
    @Override
    public String toString() {
        return this.printable;
    }
}
