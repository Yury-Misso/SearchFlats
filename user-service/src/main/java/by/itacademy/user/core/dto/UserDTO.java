package by.itacademy.user.core.dto;

import by.itacademy.user.core.dto.enums.EUserRole;
import by.itacademy.user.core.dto.enums.EUserStatus;
import by.itacademy.user.core.validator.enumValidator.ValidEnum;
import by.itacademy.user.core.validator.uuidValidator.ValidUUID;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@Builder
public class UserDTO {

    @ValidUUID()
    @NotBlank(message = "UUID не должно быть пустым")
    private String uuid;

    @PositiveOrZero
    private Long dt_create;

    @PositiveOrZero
    private Long dt_update;

    @Setter
    @Email(message = "Введите валидный Email")
    @NotBlank(message = "Email не должен быть пустым")
    private String mail;

    @Setter
    @Size(min = 3, max = 36, message = "Введите ФИО (мин 2, макс 36)")
    private String fio;


    @Setter
    @ValidEnum(enumClass = EUserRole.class)
    @NotBlank(message = "Enum не должен быть пустым")
    private String role;


    @Setter
    @ValidEnum(enumClass = EUserStatus.class)
    @NotBlank(message = "Enum не должен быть пустым")
    private String status;


}
