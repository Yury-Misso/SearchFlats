package by.itacademy.user.repository;

import by.itacademy.user.repository.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {

    Optional<UserEntity> findByMail(String mail);

    @Override
    Page<UserEntity> findAll(Pageable pageable);

    boolean existsByMail(String mail);

    @Override
    Optional<UserEntity> findById(String s);

    @Override
    UserEntity saveAndFlush(UserEntity entity);

    @Override
    boolean existsById(String s);
}
