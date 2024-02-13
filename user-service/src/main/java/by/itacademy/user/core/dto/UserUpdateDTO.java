package by.itacademy.user.core.dto;

import by.itacademy.user.core.dto.enums.EUserRole;
import by.itacademy.user.core.dto.enums.EUserStatus;
import by.itacademy.user.core.validator.enumValidator.ValidEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class UserUpdateDTO {

    @Email(message = "Введите валидный Email")
    @NotBlank(message = "Email не должен быть пустым")
    private String mail;

    @Size(min = 3, max = 36, message = "Введите ФИО (мин 2, макс 36)")
    private String fio;

    @ValidEnum(enumClass = EUserRole.class)
    @NotBlank(message = "Enum не должен быть пустым")
    private String role;

    @ValidEnum(enumClass = EUserStatus.class)
    @NotBlank(message = "Enum не должен быть пустым")
    private String status;

    @Size(min = 6, max = 16, message = "Введите пароль (мин 6, макс 16)")
    private String password;


}
