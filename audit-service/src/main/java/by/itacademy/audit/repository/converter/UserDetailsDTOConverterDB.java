package by.itacademy.audit.repository.converter;

import by.itacademy.audit.core.dto.UserDetailsDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class UserDetailsDTOConverterDB implements AttributeConverter<UserDetailsDTO, String> {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(UserDetailsDTO attribute) {

        try {
            return objectMapper.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Ошибка сериализации UserDetailsDTO в JSON");
        }

    }

    @Override
    public UserDetailsDTO convertToEntityAttribute(String dbData) {

        try {
            return objectMapper.readValue(dbData, UserDetailsDTO.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Ошибка десериализации JSON в UserDetailsDTO");
        }

    }
}
