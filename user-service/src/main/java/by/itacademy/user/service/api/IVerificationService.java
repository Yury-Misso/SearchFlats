package by.itacademy.user.service.api;

import org.springframework.stereotype.Service;

@Service
public interface IVerificationService {

    boolean verifyUser(String eMail, String verificationCodeString);


}
