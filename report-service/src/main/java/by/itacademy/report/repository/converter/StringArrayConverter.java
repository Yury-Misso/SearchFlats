package by.itacademy.report.repository.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;

public class StringArrayConverter implements AttributeConverter<String[], String> {

    private final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(String[] attribute) {
        try {
            return OBJECT_MAPPER.writeValueAsString(attribute);
        } catch (Exception e) {
            throw new RuntimeException("Conversion error ", e);
        }
    }

    @Override
    public String[] convertToEntityAttribute(String dbData) {
        try {
            return OBJECT_MAPPER.readValue(dbData, String[].class);
        } catch (Exception e) {
            throw new RuntimeException("Conversion error ", e);
        }
    }
}
