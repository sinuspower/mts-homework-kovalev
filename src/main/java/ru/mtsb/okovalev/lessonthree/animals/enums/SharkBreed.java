package ru.mtsb.okovalev.lessonthree.animals.enums;

/**
 * Перечисление пород акул.
 */
@SuppressWarnings("unused")
public enum SharkBreed {
    ANGEL_SHARK("Angel shark"), BLACKTIP_SHARK("Blacktip shark"), BULL_SHARK("Bull shark"),
    GALAPAGOS_SHARK("Galapagos shark"), LEMON_SHARK("Lemon shark"), LEOPARD_SHARK("Leopard shark"),
    SANDBAR_SHARK("Sandbar shark"), WHALE_SHARK("Whale shark"), WHITE_SHARK("White shark"),
    ZEBRA_SHARK("Zebra shark");

    private final String printable;

    SharkBreed(String printable) {
        this.printable = printable;
    }

    /**
     * Возвращает представление породы акулы, применимое для человекочитаемого вывода.
     *
     * @return порода акулы в формате для человекочитаемого вывода
     */
    @Override
    public String toString() {
        return this.printable;
    }
}
