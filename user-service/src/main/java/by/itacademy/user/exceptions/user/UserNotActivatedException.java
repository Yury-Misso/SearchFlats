package by.itacademy.user.exceptions.user;

public class UserNotActivatedException extends RuntimeException {

    public UserNotActivatedException() {
    }

    public UserNotActivatedException(String message) {
        super(message);
    }
}
