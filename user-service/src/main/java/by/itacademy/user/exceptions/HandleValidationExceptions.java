package by.itacademy.user.exceptions;

import by.itacademy.user.exceptions.errors.Error;
import by.itacademy.user.exceptions.errors.ErrorResponse;
import by.itacademy.user.exceptions.errors.StructuredErrorResponse;
import by.itacademy.user.exceptions.exceptions.NotValidUUIDException;
import by.itacademy.user.exceptions.exceptions.PageException;
import by.itacademy.user.exceptions.exceptions.UserNotExistException;
import by.itacademy.user.exceptions.exceptions.UuuupsException;
import by.itacademy.user.exceptions.token.TokenNotFoundException;
import by.itacademy.user.exceptions.token.TokenNotValidException;
import by.itacademy.user.exceptions.user.NotValidLoginException;
import by.itacademy.user.exceptions.user.UserControllerIsExistEmailException;
import by.itacademy.user.exceptions.user.UserNotActivatedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class HandleValidationExceptions {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StructuredErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<Error> errors = ex.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> new Error(fieldError.getField(), fieldError.getDefaultMessage()))
                .toList();

        return ResponseEntity.badRequest().body(new StructuredErrorResponse("structured_error", errors.toArray(Error[]::new)));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {

        return ResponseEntity.badRequest().body(new ErrorResponse("error",
                "Запрос содержит некорректные данные. Измените запрос и отправьте его ещё раз"));
    }

    @ExceptionHandler(UserControllerIsExistEmailException.class)
    public ResponseEntity<ErrorResponse> userControllerIsExistEmailException(UserControllerIsExistEmailException ex) {
        return ResponseEntity.badRequest().body(new ErrorResponse("error",
                "Запрос содержит некорректные данные. Измените запрос и отправьте его ещё раз. "
                        + ex.getMessage()));
    }

    @ExceptionHandler(NotValidLoginException.class)
    public ResponseEntity<ErrorResponse> notValidLoginException(NotValidLoginException ex) {
        return ResponseEntity.badRequest().body(new ErrorResponse("error",
                "Запрос содержит некорректные данные. Введите правильный логин и пароль и отправьте его ещё раз. "));
    }

    @ExceptionHandler(UserNotActivatedException.class)
    public ResponseEntity<ErrorResponse> userNotActivatedException(UserNotActivatedException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponse("error",
                "Данному токену авторизации запрещено выполнять запроса на данный адрес"));
    }

    @ExceptionHandler(TokenNotFoundException.class)
    public ResponseEntity<ErrorResponse> tokenNotFoundException(TokenNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorResponse("error",
                "Для выполнения запроса на данный адрес требуется передать токен авторизации"));
    }

    @ExceptionHandler(TokenNotValidException.class)
    public ResponseEntity<ErrorResponse> tokenNotValidException(TokenNotValidException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("error",
                "Запрос некорректен. Сервер не может обработать запрос"));
    }

//    @ExceptionHandler(AccessDeniedException.class)
//    public ResponseEntity<ErrorResponse> tokenException(AccessDeniedException ex) {
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("error",
//                "Запрос некорректен. Сервер не может обработать запрос"));
//    }

    @ExceptionHandler(PageException.class)
    public ResponseEntity<ErrorResponse> pageException(PageException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("error",
                "Запрос содержит некорректные данные. Измените запрос и отправьте его ещё раз"));
    }

    @ExceptionHandler(NotValidUUIDException.class)
    public ResponseEntity<ErrorResponse> notValidUUIDException(NotValidUUIDException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("error",
                "Запрос содержит некорректные данные. Измените запрос и отправьте его ещё раз"));
    }

    @ExceptionHandler(UserNotExistException.class)
    public ResponseEntity<ErrorResponse> userNotExistException(UserNotExistException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("error",
                "Пользователя с таким id не существует"));
    }

    @ExceptionHandler(UuuupsException.class)
    public ResponseEntity<ErrorResponse> uuuupsException(UuuupsException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("error",
                "Упс что-то пошло не так. Повторите запрос"));
    }

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ErrorResponse> handleAllUncaughtException(Exception ex) {
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                .body(new ErrorResponse("error",
//                        "Сервер не смог корректно обработать запрос. Пожалуйста обратитесь к администратору."));
//    }

}
