package by.itacademy.parser_sale.service.api;

import by.itacademy.parser_sale.dao.entity.FlatEntity;
import by.itacademy.parser_sale.dao.entity.FlatEntityRaw;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface IFlatService {

    Optional<FlatEntity> findByIdBySite(String idBySite);

    Optional<FlatEntity> findByUuid(String uuid);

    boolean saveRaw(FlatEntityRaw flatEntityRaw);

    void save(FlatEntity flatEntity);


}
