package ru.mtsb.okovalev.lessonnine.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import ru.mtsb.okovalev.lessonthree.animals.*;

import java.io.IOException;

/**
 * Кастомный JSON-десериализатор объектов типа Animal.
 */
public class AnimalDeserializer extends StdDeserializer<Animal> {
    protected AnimalDeserializer() {
        super(Animal.class);
    }

    /**
     * Выполняет десериализацию JSON в объект-реализацию интерфейса Animal
     * в зависимости от значения поля JSON.type
     *
     * @param jsonParser             {@link JsonParser}
     * @param deserializationContext {@link DeserializationContext}
     * @return Одна из реализаций интерфейса {@link Animal}: {@link Cat}, {@link Dog}, {@link Shark} или {@link Wolf}
     * @throws IOException если произошло исключение при чтении объекта из Json в экземпляр класса
     */
    @Override
    public Animal deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonNode jsonTree = jsonParser.getCodec().readTree(jsonParser);
        String jsonString = jsonTree.toString();
        String animalType = jsonTree.get("type").asText();
        var objectMapper = new ObjectMapper().findAndRegisterModules(); // with support for DateTime formatting

        switch (animalType) {
            case "CAT":
                return objectMapper.readValue(jsonString, Cat.class);
            case "DOG":
                return objectMapper.readValue(jsonString, Dog.class);
            case "SHARK":
                return objectMapper.readValue(jsonString, Shark.class);
            case "WOLF":
                return objectMapper.readValue(jsonString, Wolf.class);
        }

        return null; // by default
    }
}
