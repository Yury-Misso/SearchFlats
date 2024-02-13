package by.itacademy.flats.exception.token;

public class TokenNotValidException extends RuntimeException {

    public TokenNotValidException() {
    }

    public TokenNotValidException(String message) {
        super(message);
    }
}
