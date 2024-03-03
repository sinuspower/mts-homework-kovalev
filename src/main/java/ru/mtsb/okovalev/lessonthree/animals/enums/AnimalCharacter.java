package ru.mtsb.okovalev.lessonthree.animals.enums;

public enum AnimalCharacter {
    AMBITIOUS("ambitious"), AMORPHOUS("amorphous"), APATHETIC("apathetic"), BLOOD("blood"), CHOLERIC("choleric"),
    NERVOUS("nervous"), PASSIONATE("passionate"), PHLEGMATIC("phlegmatic"), SENTIMENTAL("sentimental"),
    STRONG("strong");

    private final String printable;

    AnimalCharacter(String printable) {
        this.printable = printable;
    }

    @Override
    public String toString() {
        return this.printable;
    }
}
