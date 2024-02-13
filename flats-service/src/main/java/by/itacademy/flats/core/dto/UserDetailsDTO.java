package by.itacademy.flats.core.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
public class UserDetailsDTO {

    private String uuid;
    private String mail;
    private String fio;
    private String role;

}
