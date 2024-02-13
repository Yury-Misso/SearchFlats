package by.itacademy.report.exception;

public class EntityExistExeption extends RuntimeException{
    public EntityExistExeption() {
    }

    public EntityExistExeption(String message) {
        super(message);
    }
}
