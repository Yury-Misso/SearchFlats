package by.itacademy.user.exceptions.exceptions;

public class UserNotExistException extends NullPointerException {
    public UserNotExistException() {
    }

    public UserNotExistException(String s) {
        super(s);
    }
}
