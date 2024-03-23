package ru.mtsb.okovalev.lessonthree.util;

import ru.mtsb.okovalev.lessonthree.animals.Animal;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Утилитарные методы для работы с наборами животных.
 */
public class Animals {
    /**
     * Возвращает компактный JSON, соответсвующий представлению набора животных в виде списка.
     * На вход принимает массив, который создаётся методами "create":
     * {@link ru.mtsb.okovalev.lessonthree.CreateAnimalsServiceImpl#create()},
     * {@link ru.mtsb.okovalev.lessonthree.CreateAnimalsServiceImpl#create(int)}.
     *
     * @param animals Массив, содержащий объекты Animal
     * @return компактный JSON, соответствующий исходному массиву
     */
    public static String asJson(ArrayList<Animal> animals) {
        if (Objects.isNull(animals) || animals.isEmpty()) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder("[\n");
        int n = animals.size(), i = 0;
        for (Animal animal : animals) {
            sb.append("\t").append(animal.toString());
            if (++i < n) {
                sb.append(",\n");
            } else {
                sb.append("\n");
            }
        }
        sb.append("]");

        return sb.toString();
    }

    /**
     * Возвращает компактный JSON, соответсвующий представлению набора
     * животных в виде ассоциативного массива по типам.
     * На вход принимает ассоциативный массив, который создаётся методами "createMap":
     * {@link ru.mtsb.okovalev.lessonthree.CreateAnimalsServiceImpl#createMap()},
     * {@link ru.mtsb.okovalev.lessonthree.CreateAnimalsServiceImpl#createMap(int)}.
     *
     * @param animals Ассоциативный массив, содержащий объекты Animal
     * @return компактный JSON, соответствующий исходному массиву
     */
    public static String asJson(Map<String, List<Animal>> animals) {
        if (Objects.isNull(animals) || animals.isEmpty()) {
            return "{}";
        }

        StringBuilder sb = new StringBuilder("{\n");
        int n = animals.size(), m, i = 0, j;
        List<Animal> animalsList;
        for (Map.Entry<String, List<Animal>> entry : animals.entrySet()) {
            sb.append("\t\"").append(entry.getKey()).append("\": ");
            animalsList = entry.getValue();

            if (Objects.isNull(animalsList)) {
                sb.append("null");
            } else if (animalsList.isEmpty()) {
                sb.append("[]");
            } else {
                sb.append("[\n");
                j = 0;
                m = animalsList.size();
                for (Animal animal : animalsList) {
                    sb.append("\t\t").append(animal.toString());
                    if (++j < m) {
                        sb.append(",\n");
                    } else {
                        sb.append("\n");
                    }
                }
                sb.append("\t]");
            }

            if (++i < n) {
                sb.append(",\n");
            } else {
                sb.append("\n");
            }
        }
        sb.append("}");

        return sb.toString();
    }
}
