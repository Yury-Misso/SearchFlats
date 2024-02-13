package by.itacademy.user.service.impl;

import by.itacademy.user.core.dto.EmailVerificationCodeDTO;
import by.itacademy.user.exceptions.redisService.RedisServiceException;
import by.itacademy.user.service.api.IRedisService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RedisService implements IRedisService {

    private final StringRedisTemplate redisTemplate;

    private final ObjectMapper objectMapper;


    public RedisService(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public void save(EmailVerificationCodeDTO emailVerificationCodeDTO) {
        String key = emailVerificationCodeDTO.getEmail();
        String value = emailVerificationCodeDTO.getVerificationCode().toString();

        redisTemplate.opsForValue().set(key, value);

    }

    @Override
    public void delete(String eMail) {

        redisTemplate.delete(eMail);

    }

    @Override
    public UUID getValue(String eMail) {

        String value = redisTemplate.opsForValue().get(eMail);

        if (value != null) {
            return UUID.fromString(value);
        } else {
            throw new RedisServiceException("Пользователь с таким eMail не существует");
        }
    }

    @Override
    public boolean exist(String eMail) {

        String value = redisTemplate.opsForValue().get(eMail);

        return value != null;
    }
}
