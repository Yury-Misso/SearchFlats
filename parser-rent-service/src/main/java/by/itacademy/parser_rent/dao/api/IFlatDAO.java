package by.itacademy.parser_rent.dao.api;

import by.itacademy.parser_rent.dao.entity.FlatEntity;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IFlatDAO extends ListCrudRepository<FlatEntity, String> {

    Optional<FlatEntity> findByIdBySite(String idBySite);

    Optional<FlatEntity> findByUuid(String uuid);

    FlatEntity save(@NonNull FlatEntity FlatEntity);

}
