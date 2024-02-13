package by.itacademy.user.exceptions.errors;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ErrorResponse {
    private String logref;
    private String message;
}
