package by.itacademy.user.core.dto.converter;

import by.itacademy.user.core.dto.UserAddDTO;
import by.itacademy.user.core.dto.UserCreateDTO;
import by.itacademy.user.core.dto.UserRegistrationDTO;

public class ConvertUserAddDTOToUserCreateDTO {

    public UserCreateDTO convert(UserAddDTO userAddDTO) {

        return UserCreateDTO.builder()
                .mail(userAddDTO.getMail())
                .fio(userAddDTO.getFio())
                .role(userAddDTO.getRole())
                .status((userAddDTO.getStatus()))
                .password(userAddDTO.getPassword())
                .build();
    }
}
