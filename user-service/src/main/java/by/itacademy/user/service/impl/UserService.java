package by.itacademy.user.service.impl;

import by.itacademy.user.aop.Audit;
import by.itacademy.user.aop.eaudit.EssenceType;
import by.itacademy.user.aop.eaudit.Text;
import by.itacademy.user.core.dto.UserCreateDTO;
import by.itacademy.user.core.dto.UserDTO;
import by.itacademy.user.core.dto.UserUpdateDTO;
import by.itacademy.user.core.dto.enums.EUserRole;
import by.itacademy.user.core.dto.enums.EUserStatus;
import by.itacademy.user.exceptions.exceptions.UserNotExistException;
import by.itacademy.user.exceptions.exceptions.UuuupsException;
import by.itacademy.user.exceptions.user.UserControllerIsExistEmailException;
import by.itacademy.user.repository.UserRepository;
import by.itacademy.user.repository.entity.UserEntity;
import by.itacademy.user.repository.entity.UserEntityBuilder;
import by.itacademy.user.service.api.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService implements IUserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final ModelMapper modelMapper;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    @Audit(essenceType = EssenceType.USER, text = Text.CREATE_USER)
    public UserDTO createUser(UserCreateDTO userCreateDTO) {

        String mail = userCreateDTO.getMail();

        if (existsByMail(mail)) {
            throw new UserControllerIsExistEmailException("Пользователь с таким Email уже существует");
        }

        UUID uuid = UUID.randomUUID();

        Long nowLong = System.currentTimeMillis();

        UserEntity userEntity = new UserEntityBuilder()
                .setUuid(uuid.toString())
                .setDt_create(nowLong)
                .setDt_update(nowLong)
                .setMail(userCreateDTO.getMail())
                .setFio(userCreateDTO.getFio())
                .setRole(EUserRole.USER.name())
                .setStatus(EUserStatus.WAITING_ACTIVATION.name())
                .setPassword(passwordEncoder.encode(userCreateDTO.getPassword()))
                .build();

        userRepository.saveAndFlush(userEntity);

        return new UserDTO().builder()
                .uuid(userEntity.getUuid())
                .dt_create(userEntity.getDt_create())
                .dt_update(userEntity.getDt_update())
                .mail(userEntity.getMail())
                .fio(userEntity.getFio())
                .role(userEntity.getRole())
                .status(userEntity.getStatus())
                .build();

    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserDTO> findByMail(String mail) {
        Optional<UserEntity> byMail = userRepository.findByMail(mail);
        UserDTO userDTO = new UserDTO();
        if (byMail.isPresent()) {
            userDTO = UserDTO.builder()
                    .uuid(byMail.get().getUuid())
                    .dt_create(byMail.get().getDt_create())
                    .dt_update(byMail.get().getDt_update())
                    .mail(byMail.get().getMail())
                    .fio(byMail.get().getFio())
                    .role(byMail.get().getRole())
                    .status(byMail.get().getStatus())
                    .build();
        }

        return Optional.of(userDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserEntity> findEntityByMail(String mail) {
        return userRepository.findByMail(mail);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByMail(String mail) {
        return userRepository.existsByMail(mail);
    }

    @Override
    @Transactional
    @Audit(essenceType = EssenceType.USER, text = Text.VERIFICATION)
    public boolean updateVerificationStatusByMail(String eMail) {
        Optional<UserEntity> entityByMail = findEntityByMail(eMail);

        if (entityByMail.isPresent()) {
            UserEntity userEntity = entityByMail.get();

            userEntity.setStatus(EUserStatus.ACTIVATED.name());

            userRepository.saveAndFlush(userEntity);
            return true;
        } else {
            return false;
        }

    }


    @Override
    @Transactional(readOnly = true)
    @Audit(essenceType = EssenceType.USER, text = Text.ABOUT_ME)
    public UserDTO aboutMe(String mail) {
        return findByMail(mail).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    @Audit(essenceType = EssenceType.USER, text = Text.ABOUT_ALL)
    public Page<UserDTO> findAll(Pageable pageable) {

        Page<UserEntity> all = userRepository.findAll(pageable);
        return all.map(entity -> modelMapper.map(entity, UserDTO.class));

    }

    @Override
    @Transactional(readOnly = true)
    @Audit(essenceType = EssenceType.USER, text = Text.ABOUT_USER)
    public UserDTO findById(String id) {

        Optional<UserEntity> byId = userRepository.findById(id);

        UserDTO userDTO;

        if (byId.isPresent()) {
            userDTO = modelMapper.map(byId, UserDTO.class);
        } else {
            throw new UserNotExistException();
        }

        return userDTO;
    }

    @Override
    @Transactional
    @Audit(essenceType = EssenceType.USER, text = Text.UPDATE_USER)
    public UserDTO updateUser(String uuid, Long dtUpdate, UserUpdateDTO userUpdateDTO) {

        Optional<UserEntity> byId = userRepository.findById(uuid);

        if (byId.isPresent()) {
            UserEntity userEntity = byId.get();

            updateUserEntityFromDTO(userUpdateDTO, userEntity);

            userEntity.setPassword(passwordEncoder.encode(userUpdateDTO.getPassword()));
            userEntity.setDt_update(dtUpdate);

            userRepository.saveAndFlush(userEntity);

            return modelMapper.map(userEntity, UserDTO.class);

        } else {

            UserEntity userEntity = modelMapper.map(userUpdateDTO, UserEntity.class);

            userEntity.setUuid(UUID.randomUUID().toString());
            userEntity.setDt_create(System.currentTimeMillis());
            userEntity.setDt_update(dtUpdate);
            userEntity.setPassword(passwordEncoder.encode(userUpdateDTO.getPassword()));

            if (userRepository.existsById(userEntity.getUuid())) {
                throw new UuuupsException();
            }

            userRepository.saveAndFlush(userEntity);

            return modelMapper.map(userEntity, UserDTO.class);
        }

    }

    private UserEntity updateUserEntityFromDTO(UserUpdateDTO userUpdateDTO,
                                               UserEntity userEntity) {
        modelMapper.map(userUpdateDTO, userEntity);
        return userEntity;
    }

}
