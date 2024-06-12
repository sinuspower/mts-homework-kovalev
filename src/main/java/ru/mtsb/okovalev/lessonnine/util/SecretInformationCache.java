package ru.mtsb.okovalev.lessonnine.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.stream.Stream;

/**
 * Предназначен для кеширования секретной информации из файла.
 * Каждый элемент кеша – одна строка файла.
 */
public class SecretInformationCache {
    private final ArrayList<String> cache = new ArrayList<>();

    /**
     * Выполняет инициализацию кеша из указанного файла.
     * Каждая строка файла читается в о
     *
     * @param path Путь к файлу с секретной информацией
     * @throws IOException если произошло исключение во время чтения файла
     */
    public void init(Path path) throws IOException {
        try (Stream<String> s = Files.lines(path)) {
            s.forEach(cache::add);
        }
    }

    /**
     * Возвращает текущий размер кеша.
     *
     * @return Размер кеша
     */
    public int size() {
        return cache.size();
    }

    /**
     * Возвращает информацию из кеша по индексу.
     *
     * @param i Индекс элемента
     * @return Секретная строка
     */
    public String get(int i) {
        return cache.get(i);
    }
}
