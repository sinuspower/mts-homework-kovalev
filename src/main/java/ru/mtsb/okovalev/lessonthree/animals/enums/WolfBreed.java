package ru.mtsb.okovalev.lessonthree.animals.enums;

public enum WolfBreed {
    ARCTIC_WOLF("Arctic wolf"), EASTERN_WOLF("Eastern wolf"), HIMALAYAN_WOLF("Himalayan wolf"),
    MEXICAN_WOLF("Mexican wolf"), RED_WOLF("Red wolf"), ARABIAN_WOLF("Arabian wolf"),
    EURASIAN_WOLF("Eurasian wolf"), INDIAN_WOLF("Indian wolf"), NORTHWESTERN_WOLF("Northwestern wolf"),
    BLACK_WOLF("Black wolf");

    private final String printable;

    WolfBreed(String printable) {
        this.printable = printable;
    }

    @Override
    public String toString() {
        return this.printable;
    }
}
