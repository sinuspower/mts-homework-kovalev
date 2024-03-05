package ru.mtsb.okovalev.lessonthree.animals.enums;

/**
 * Перечисление пород собак.
 */
@SuppressWarnings("unused")
public enum DogBreed {
    AFFENPINSCHER("Affenpinscher"), AKITA("Akita"), AUSTRALIAN_SHEPHERD("Australian Shepherd"),
    BASSET_HOUND("Basset Hound"), BEAGLE("Beagle"), BIEWER_TERRIER("Biewer Terrier"), BOXER("Boxer"),
    BULLDOG("Bulldog"), BULL_TERRIER("Bull Terrier"), CANE_CORSO("Cane Corso");

    private final String printable;

    DogBreed(String printable) {
        this.printable = printable;
    }

    /**
     * Возвращает представление породы собаки, применимое для человекочитаемого вывода.
     *
     * @return порода собаки в формате для человекочитаемого вывода
     */
    @Override
    public String toString() {
        return this.printable;
    }
}
