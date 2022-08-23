package com.box.sdk;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 *
 */
final class BoxJsonMapper {
    private static final BoxJsonMapper INSTANCE = new BoxJsonMapper();
    private final ObjectMapper objectMapper;

    private BoxJsonMapper() {
        this.objectMapper = new ObjectMapper();
    }

    public static BoxJsonMapper getInstance() {
        return INSTANCE;
    }

    JsonNode parse(String jsonString) {
        try {
            return objectMapper.readTree(jsonString);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Couldn't parse JSON", e);
        }
    }

    String valueToString(Object value) {
        try {
            return this.objectMapper.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Couldn't serialize object", e);
        }
    }
}
