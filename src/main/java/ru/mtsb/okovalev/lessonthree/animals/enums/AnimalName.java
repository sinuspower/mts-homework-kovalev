package ru.mtsb.okovalev.lessonthree.animals.enums;

public enum AnimalName {
    FLOWER("Flower"), BAMBI("Bambi"), CUDDLES("Cuddles"), LOLA("Lola"), TWEETY("Tweety"), ANGEL("Angel"), CESAR("Cesar"), DAISY("Daisy"), TUTU("Tutu"), BABY("Baby");

    private final String printable;

    AnimalName(String printable) {
        this.printable = printable;
    }

    @Override
    public String toString() {
        return this.printable;
    }
}
