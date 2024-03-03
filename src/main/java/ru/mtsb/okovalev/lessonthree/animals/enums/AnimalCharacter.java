package ru.mtsb.okovalev.lessonthree.animals.enums;

/**
 * Перечисление поведенческих особенностей (характера) животного.
 */
@SuppressWarnings("unused")
public enum AnimalCharacter {
    AMBITIOUS("ambitious"), AMORPHOUS("amorphous"), APATHETIC("apathetic"), BLOOD("blood"), CHOLERIC("choleric"),
    NERVOUS("nervous"), PASSIONATE("passionate"), PHLEGMATIC("phlegmatic"), SENTIMENTAL("sentimental"),
    STRONG("strong");

    private final String printable;

    AnimalCharacter(String printable) {
        this.printable = printable;
    }

    /**
     * Возвращает представление характера животного, применимое для человекочитаемого вывода.
     *
     * @return характер животного в формате для человекочитаемого вывода
     */
    @Override
    public String toString() {
        return this.printable;
    }
}
