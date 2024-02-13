package by.itacademy.user.controller;

import by.itacademy.user.core.dto.*;
import by.itacademy.user.core.dto.converter.ConvertUserAddDTOToUserCreateDTO;
import by.itacademy.user.core.dto.converter.ConvertUserRegistrationDTOToUserCreateDTO;
import by.itacademy.user.core.dto.enums.EUserStatus;
import by.itacademy.user.core.validator.uuidValidator.ValidUUID;
import by.itacademy.user.exceptions.exceptions.NotValidUUIDException;
import by.itacademy.user.exceptions.exceptions.PageException;
import by.itacademy.user.exceptions.token.TokenNotFoundException;
import by.itacademy.user.service.AuthenticationService;
import by.itacademy.user.service.api.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/users")
public class UserController {

    private final IUserService iUserService;
    private final IRabbitMQServiceRequest iRabbitMQServiceRequest;
    private final IRedisService redisService;
    private final IVerificationService verificationService;
    private final AuthenticationService authenticationService;

    private final Logger logger = LoggerFactory.getLogger(UserController.class);


    public UserController(@Qualifier("userService") IUserService iUserService,
                          @Qualifier("rabbitMQServiceRequest") IRabbitMQServiceRequest iRabbitMQServiceRequest,
                          @Qualifier("redisService") IRedisService redisService,
                          @Qualifier("verificationService") IVerificationService verificationService,
                          AuthenticationService authenticationService

    ) {
        this.iUserService = iUserService;
        this.iRabbitMQServiceRequest = iRabbitMQServiceRequest;
        this.redisService = redisService;
        this.verificationService = verificationService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/registration")
    public ResponseEntity<String> registerUser(@Validated @RequestBody UserRegistrationDTO userRegistrationDTO) {

        UserDTO userDTO = iUserService.createUser(new ConvertUserRegistrationDTOToUserCreateDTO().convert(userRegistrationDTO));

        EmailVerificationCodeDTO emailVerificationCodeDTO =
                new EmailVerificationCodeDTO(userDTO.getMail(), UUID.randomUUID());

        iRabbitMQServiceRequest.sendRequest(emailVerificationCodeDTO);

        redisService.save(emailVerificationCodeDTO);

        return new ResponseEntity<>("Пользователь зарегистрирован", HttpStatus.CREATED);
    }

    @GetMapping("/verification")
    public ResponseEntity<String> verifyUser(@RequestParam("code") String verificationCode,
                                             @RequestParam("mail") String email) {

        boolean b = verificationService.verifyUser(email, verificationCode);

        if (b) {
            return new ResponseEntity<>("Пользователь верифицирован", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Код отсутствует или введен неверно", HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> loginUser(@Validated @RequestBody UserLoginDTO userLoginDTO) {

        String token = authenticationService.signIn(userLoginDTO).getToken();
        String message = "Вход выполнен. Токен для Authorization Header";
        LoginResponseDTO loginResponseDTO = new LoginResponseDTO(message, token);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(loginResponseDTO);

    }


    @GetMapping("/me")
    public ResponseEntity<UserDTO> aboutMe() {

        try {
            UserDetailsDTO principal = (UserDetailsDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String mail = principal.getMail();
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(iUserService.aboutMe(mail));
        } catch (NullPointerException e) {
            throw new TokenNotFoundException();
        }

    }

    @PostMapping("/")
    @Secured("ADMIN")
    public ResponseEntity<String> addUser(@Validated @RequestBody UserAddDTO userAddDTO) {

        UserDTO userDTO = iUserService.createUser(new ConvertUserAddDTOToUserCreateDTO().convert(userAddDTO));

        if (!userAddDTO.getStatus().equalsIgnoreCase(EUserStatus.ACTIVATED.name())) {

            EmailVerificationCodeDTO emailVerificationCodeDTO =
                    new EmailVerificationCodeDTO(userDTO.getMail(), UUID.randomUUID());

            iRabbitMQServiceRequest.sendRequest(emailVerificationCodeDTO);

            redisService.save(emailVerificationCodeDTO);
        }

        return new ResponseEntity<>("Пользователь зарегистрирован", HttpStatus.CREATED);

    }

    @GetMapping("/")
    @Secured("ADMIN")
    public ResponseEntity<PageDTOCustom<UserDTO>> getUsers(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                                           @RequestParam(value = "size", defaultValue = "20") Integer size) {

        Pageable pageable;
        try {
            pageable = PageRequest.of(page, size);
        } catch (IllegalArgumentException e) {
            throw new PageException();
        }

        PageDTOCustom<UserDTO> customPage = new PageDTOCustom<>();

        customPage.convert(iUserService.findAll(pageable));

        return ResponseEntity.ok(customPage);

    }

    @GetMapping("/{id}")
    @Secured("ADMIN")
    public ResponseEntity<UserDTO> getUsersById(@PathVariable String id) {

        UUID uuid;

        try {
            uuid = UUID.fromString(id);
        } catch (IllegalArgumentException e) {
            throw new NotValidUUIDException();
        }

        UserDTO byId = iUserService.findById(uuid.toString());

        return ResponseEntity.ok(byId);

    }

    @PutMapping("/{id}/dt_update/{dt_update}")
    @Secured("ADMIN")
    public ResponseEntity<String> userUpdate(@ValidUUID()
                                             @NotBlank(message = "UUID не должно быть пустым")
                                             @PathVariable(name = "id") String id,

                                             @PositiveOrZero
                                             @PathVariable(name = "dt_update") Long dtUpdate,

                                             @Validated @RequestBody UserUpdateDTO userUpdateDTO) {

        UserDTO userDTO = iUserService.updateUser(id, dtUpdate, userUpdateDTO);

        if (!userDTO.getStatus().equalsIgnoreCase(EUserStatus.ACTIVATED.name())) {

            EmailVerificationCodeDTO emailVerificationCodeDTO =
                    new EmailVerificationCodeDTO(userDTO.getMail(), UUID.randomUUID());

            iRabbitMQServiceRequest.sendRequest(emailVerificationCodeDTO);

            redisService.save(emailVerificationCodeDTO);
        }

        return ResponseEntity.ok("Пользователь обновлён");

    }


}
