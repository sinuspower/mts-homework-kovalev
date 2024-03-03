package ru.mtsb.okovalev.lessonthree.animals.enums;

/**
 * Перечисление пород кошек.
 */
@SuppressWarnings("unused")
public enum CatBreed {
    BALINESE("Balinese"), BIRMAN("Birman"), BURMESE("Burmese"), BURMILLA("Burmilla"), CHARTREUX("Chartreux"),
    CORNISH_REX("Cornish Rex"), JAVANESE("Javanese"), PERSIAN("Persian"), SIBERIAN("Siberian"), SPHYNX("Sphynx");

    private final String printable;

    CatBreed(String printable) {
        this.printable = printable;
    }

    /**
     * Возвращает представление породы кошки, применимое для человекочитаемого вывода.
     *
     * @return порода кошки в формате для человекочитаемого вывода
     */
    @Override
    public String toString() {
        return this.printable;
    }
}
