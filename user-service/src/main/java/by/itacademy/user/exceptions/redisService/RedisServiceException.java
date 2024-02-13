package by.itacademy.user.exceptions.redisService;

public class RedisServiceException extends IllegalArgumentException {
    public RedisServiceException() {
    }

    public RedisServiceException(String message) {
        super(message);
    }
}
