package by.itacademy.user.exceptions.token;

public class TokenNotValidException extends RuntimeException {

    public TokenNotValidException() {
    }

    public TokenNotValidException(String message) {
        super(message);
    }
}
