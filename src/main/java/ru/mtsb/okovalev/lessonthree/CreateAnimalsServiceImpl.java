package ru.mtsb.okovalev.lessonthree;

import ru.mtsb.okovalev.lessonthree.animals.Animal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Имплементация интерфейса CreateAnimalsService.
 * Переопределяет метод create() интерфейса по умолчанию.
 * Добавляет собственный метод create(n) для создания n псевдослучайных животных.
 */
public class CreateAnimalsServiceImpl implements CreateAnimalsService {
    /**
     * Возвращает массив псевдослучайных животных размера по умолчанию.
     * Для заполнения массива используется цикл do-while.
     * Записывает информацию о количестве созданных животных и использованном
     * для этого методе в стандартный поток вывода.
     *
     * @return массив псевдослучайных животных размера по умолчанию
     */
    @Override
    public ArrayList<Animal> create() {
        ArrayList<Animal> animals = new ArrayList<>();

        int i = 0;
        do {
            animals.add(randomAnimal());
            i++;
        } while (i < DEFAULT_ANIMALS_COUNT);

        return animals;
    }

    /**
     * Возвращает ассоциативный массив псевдослучайных животных,
     * содержащий количество объектов по умолчанию.
     *
     * @return ключ - тип животного, значение - список псевдослучайных животных этого типа
     */
    @Override
    public Map<String, List<Animal>> createMap() {
        return createMap(DEFAULT_ANIMALS_COUNT);
    }

    /**
     * Возвращает массив псевдослучайных животных размера n.
     * Для заполнения массива используется цикл for.
     * Записывает информацию о количестве созданных животных и использованном
     * для этого методе в стандартный поток вывода.
     *
     * @param n Размер результирующего массива псевдослучайных животных
     * @return массив псевдослучайных животных размера n
     */
    public ArrayList<Animal> create(int n) {
        ArrayList<Animal> animals = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            animals.add(randomAnimal());
        }

        return animals;
    }

    /**
     * Возвращает ассоциативный массив псевдослучайных животных,
     * содержащий в общей сложности заданное количество объектов.
     *
     * @param n Количество животных, которых необходимо создать
     * @return ключ - тип животного, значение - список псевдослучайных животных этого типа
     */
    public Map<String, List<Animal>> createMap(int n) {
        HashMap<String, List<Animal>> animals = new HashMap<>();

        Animal animal;
        ArrayList<Animal> animalsList;
        String animalType;
        for (int i = 0; i < n; i++) {
            animal = randomAnimal();
            animalType = animal.getType().toString();

            if (animals.containsKey(animalType)) {
                animals.get(animalType).add(animal);
            } else {
                animalsList = new ArrayList<>();
                animalsList.add(animal);
                animals.put(animal.getType().toString(), animalsList);
            }
        }

        return animals;
    }

    /**
     * Возвращает компактный JSON, соответсвующий представлению набора животных в виде списка.
     * На вход принимает массив, который создаётся методами "create":
     * {@link #create()}, {@link #create(int)}.
     *
     * @param animals Массив, содержащий объекты Animal
     * @return компактный JSON, соответствующий исходному массиву
     */
    public String asJson(ArrayList<Animal> animals) {
        if (animals.isEmpty()) {
            return "";
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
     * {@link #createMap()}, {@link #createMap(int)}.
     *
     * @param animals Ассоциативный массив, содержащий объекты Animal
     * @return компактный JSON, соответствующий исходному массиву
     */
    public String asJson(Map<String, List<Animal>> animals) {
        if (animals.isEmpty()) {
            return "";
        }

        StringBuilder sb = new StringBuilder("{\n");
        int n = animals.size(), m, i = 0, j;
        List<Animal> animalsList;
        for (Map.Entry<String, List<Animal>> entry : animals.entrySet()) {
            sb.append("\t\"").append(entry.getKey()).append("\": [\n");
            animalsList = entry.getValue();

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

            if (++i < n) {
                sb.append("\t],\n");
            } else {
                sb.append("\t]\n");
            }
        }
        sb.append("}");

        return sb.toString();
    }
}
