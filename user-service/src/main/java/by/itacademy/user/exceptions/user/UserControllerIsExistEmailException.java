package by.itacademy.user.exceptions.user;


public class UserControllerIsExistEmailException extends IllegalArgumentException {

    public UserControllerIsExistEmailException() {
    }

    public UserControllerIsExistEmailException(String message) {
        super(message);
    }
}
