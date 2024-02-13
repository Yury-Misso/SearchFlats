package by.itacademy.user.service;

import by.itacademy.user.aop.Audit;
import by.itacademy.user.aop.eaudit.EssenceType;
import by.itacademy.user.aop.eaudit.Text;
import by.itacademy.user.core.dto.JwtAuthenticationResponse;
import by.itacademy.user.core.dto.UserDetailsDTO;
import by.itacademy.user.core.dto.UserLoginDTO;
import by.itacademy.user.core.dto.enums.EUserStatus;
import by.itacademy.user.exceptions.exceptions.UserNotExistException;
import by.itacademy.user.exceptions.exceptions.UuuupsException;
import by.itacademy.user.exceptions.user.UserNotActivatedException;
import by.itacademy.user.repository.entity.UserEntity;
import by.itacademy.user.service.api.IUserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService {

    private final IUserService userService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationService(@Qualifier("userService") IUserService userService,
                                 JwtService jwtService
            , PasswordEncoder passwordEncoder
    ) {
        this.userService = userService;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    @Audit(essenceType = EssenceType.USER, text = Text.LOGIN)
    public JwtAuthenticationResponse signIn(UserLoginDTO request) {

        String mail = request.getMail();

        Optional<UserEntity> entityByMail = userService.findEntityByMail(request.getMail());

        if (entityByMail.isPresent()) {
            UserEntity userEntity = entityByMail.get();

            if (!passwordEncoder.matches(request.getPassword(), userEntity.getPassword())) {
                throw new UuuupsException();
            }

            if (!userEntity.getStatus().equalsIgnoreCase(EUserStatus.ACTIVATED.name())) {
                throw new UserNotActivatedException();
            }

            UserDetailsDTO userDetailsDTO = new UserDetailsDTO()
                    .setFio(userEntity.getFio())
                    .setMail(userEntity.getMail())
                    .setUuid(userEntity.getUuid())
                    .setRole(userEntity.getRole());

            var jwt = jwtService.generateToken(userDetailsDTO);

            return new JwtAuthenticationResponse(jwt);
        } else {
            throw new UserNotExistException();
        }

    }

}
