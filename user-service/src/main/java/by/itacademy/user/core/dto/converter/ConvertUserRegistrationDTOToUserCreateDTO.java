package by.itacademy.user.core.dto.converter;

import by.itacademy.user.core.dto.UserCreateDTO;
import by.itacademy.user.core.dto.UserRegistrationDTO;

public class ConvertUserRegistrationDTOToUserCreateDTO {

    public UserCreateDTO convert(UserRegistrationDTO userRegistrationDTO) {

        return UserCreateDTO.builder()
                .mail(userRegistrationDTO.getMail())
                .fio(userRegistrationDTO.getFio())
                .password(userRegistrationDTO.getPassword())
                .build();
    }
}
