package by.itacademy.user.exceptions.errors;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class StructuredErrorResponse {
    private String logref;
    private Error[] errors;
}
