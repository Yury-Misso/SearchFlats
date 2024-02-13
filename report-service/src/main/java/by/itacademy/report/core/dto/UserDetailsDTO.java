package by.itacademy.report.core.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;
import lombok.experimental.Accessors;

@JsonPropertyOrder({
        "uuid",
        "mail",
        "fio",
        "role"
})
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
@ToString
public class UserDetailsDTO {

    private String uuid;
    private String mail;
    private String fio;
    private String role;

}
