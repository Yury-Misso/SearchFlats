package by.itacademy.flats.core.converter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.List;

@Converter
public class StringArrayConverter implements AttributeConverter<List<String>, String> {

    private final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(List<String> attribute) {
        try {
            return OBJECT_MAPPER.writeValueAsString(attribute);
        } catch (Exception e) {
            throw new RuntimeException("Conversion error ", e);
        }
    }

    @Override
    public List<String> convertToEntityAttribute(String dbData) {
        try {
            return OBJECT_MAPPER.readValue(dbData,
                    new TypeReference<List<String>>() {
                    });
        } catch (Exception e) {
            throw new RuntimeException("Conversion error ", e);
        }
    }
}
