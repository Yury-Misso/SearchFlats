package by.itacademy.audit.exception;

public class EntityExistExeption extends RuntimeException{
    public EntityExistExeption() {
    }

    public EntityExistExeption(String message) {
        super(message);
    }
}
