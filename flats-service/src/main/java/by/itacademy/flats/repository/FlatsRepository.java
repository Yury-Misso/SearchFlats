package by.itacademy.flats.repository;

import by.itacademy.flats.repository.entity.FlatEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlatsRepository extends JpaRepository<FlatEntity, String> {


}
