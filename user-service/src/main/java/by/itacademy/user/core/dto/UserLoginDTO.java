package by.itacademy.user.core.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserLoginDTO {

    @Email(message = "Введите валидный Email")
    @NotBlank(message = "Email не должен быть пустым")
    private String mail;

    @Size(min = 6, max = 16, message = "Введите пароль (мин 6, макс 16)")
    private String password;
}
