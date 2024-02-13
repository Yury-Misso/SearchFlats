package by.itacademy.user.service.api;

import by.itacademy.user.core.dto.UserCreateDTO;
import by.itacademy.user.core.dto.UserDTO;
import by.itacademy.user.core.dto.UserUpdateDTO;
import by.itacademy.user.repository.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface IUserService {

    UserDTO createUser(UserCreateDTO userCreateDTO);

    Optional<UserDTO> findByMail(String mail);

    Optional<UserEntity> findEntityByMail(String mail);

    boolean existsByMail(String mail);

    boolean updateVerificationStatusByMail(String eMail);

    UserDTO aboutMe(String mail);

    Page<UserDTO> findAll(Pageable pageable);

    UserDTO findById(String s);

    UserDTO updateUser(String uuid,
                       Long dtUpdate,
                       UserUpdateDTO userUpdateDTO);

}
