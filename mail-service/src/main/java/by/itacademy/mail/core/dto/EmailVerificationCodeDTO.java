package by.itacademy.mail.core.dto;

import by.itacademy.mail.core.dto.validator.uuidValidator.ValidUUID;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class EmailVerificationCodeDTO {

    @JsonProperty("email")
    @Email(message = "Введите валидный Email")
    @NotBlank(message = "Email не должен быть пустым")
    private String email;

    @JsonProperty("verificationCode")
    @ValidUUID()
    @NotBlank(message = "UUID не должно быть пустым")
    private UUID verificationCode;

}
