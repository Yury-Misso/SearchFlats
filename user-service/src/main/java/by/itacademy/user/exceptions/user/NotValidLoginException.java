package by.itacademy.user.exceptions.user;

public class NotValidLoginException extends RuntimeException {

    public NotValidLoginException() {

    }

    public NotValidLoginException(String message) {
        super(message);
    }
}
