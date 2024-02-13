package by.itacademy.user.service.impl;

import by.itacademy.user.service.api.IRedisService;
import by.itacademy.user.service.api.IUserService;
import by.itacademy.user.service.api.IVerificationService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class VerificationService implements IVerificationService {

    private final IRedisService redisService;
    private final IUserService userService;

    public VerificationService(@Qualifier("redisService") IRedisService redisService,
                               @Qualifier("userService") IUserService userService) {
        this.redisService = redisService;
        this.userService = userService;
    }

    public boolean verifyUser(String eMail, String verificationCodeString) {

        if (redisService.exist(eMail)) {

            UUID code = redisService.getValue(eMail);
            UUID verificationCode = UUID.fromString(verificationCodeString);

            if (code.equals(verificationCode)) {
                userService.updateVerificationStatusByMail(eMail);
                redisService.delete(eMail);
                return true;
            }

        }

        return false;

    }

}
