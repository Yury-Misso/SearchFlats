package by.itacademy.user.exceptions.errors;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Error {
    private String field;
    private String message;
}
