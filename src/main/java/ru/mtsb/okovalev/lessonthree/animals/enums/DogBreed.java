package ru.mtsb.okovalev.lessonthree.animals.enums;

public enum DogBreed {
    AFFENPINSCHER("Affenpinscher"), AKITA("Akita"), AUSTRALIAN_SHEPHERD("Australian Shepherd"),
    BASSET_HOUND("Basset Hound"), BEAGLE("Beagle"), BIEWER_TERRIER("Biewer Terrier"), BOXER("Boxer"),
    BULLDOG("Bulldog"), BULL_TERRIER("Bull Terrier"), CANE_CORSO("Cane Corso");

    private final String printable;

    DogBreed(String printable) {
        this.printable = printable;
    }

    @Override
    public String toString() {
        return this.printable;
    }
}
