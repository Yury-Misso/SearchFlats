package by.itacademy.user.aop.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;
import lombok.experimental.Accessors;

@JsonPropertyOrder({
        "dt_create",
        "user",
        "text",
        "type",
        "id"
})
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
@ToString
public class AuditDTO {

    Long dt_create;
    String user;
    String text;
    String type;
    String id;

}
