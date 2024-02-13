package by.itacademy.parser_rent.service;

import by.itacademy.parser_rent.dao.entity.FlatEntity;
import by.itacademy.parser_rent.dao.entity.FlatEntityBuilder;
import by.itacademy.parser_rent.dao.entity.FlatEntityRaw;
import by.itacademy.parser_rent.dao.api.IFlatDAO;
import by.itacademy.parser_rent.service.api.IFlatService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
public class FlatService implements IFlatService {

    private final IFlatDAO dao;

    public FlatService(IFlatDAO dao) {
        this.dao = dao;
    }

    @Override
    @Transactional
    public Optional<FlatEntity> findByUuid(String uuid) {
        return dao.findByUuid(uuid);
    }

    @Override
    @Transactional
    public Optional<FlatEntity> findByIdBySite(String idBySite) {
        return dao.findByIdBySite(idBySite);
    }

    @Override
    @Transactional
    public void save(FlatEntity flatEntity) {
        dao.save(flatEntity);
    }

    @Override
    @Transactional
    public boolean saveRaw(FlatEntityRaw flatEntityRaw) {
        Optional<FlatEntity> flatEntityFromBaseByIdBySite = findByIdBySite(flatEntityRaw.getIdBySite());
        String uuid = UUID.randomUUID().toString();
        Optional<FlatEntity> flatEntityFromBaseByUUID = dao.findByUuid(uuid);

        if (flatEntityFromBaseByIdBySite.isEmpty()) {
            while (true) {
                if (flatEntityFromBaseByUUID.isEmpty()) {
                    break;
                }
                uuid = UUID.randomUUID().toString();
                flatEntityFromBaseByUUID = dao.findByUuid(uuid);
            }

            FlatEntity flatEntity = new FlatEntityBuilder()
                    .setUuid(uuid)
                    .setDt_create(flatEntityRaw.getDt_create())
                    .setDt_update(flatEntityRaw.getDt_update())
                    .setOffer_type(flatEntityRaw.getOffer_type())
                    .setDescription(flatEntityRaw.getDescription())
                    .setArea(flatEntityRaw.getArea())
                    .setPrice(flatEntityRaw.getPrice())
                    .setFloor(flatEntityRaw.getFloor())
                    .setBedrooms(flatEntityRaw.getBedrooms())
                    .setPhoto_urls(flatEntityRaw.getPhoto_urls())
                    .setOriginal_url(flatEntityRaw.getOriginal_url())
                    .setId_by_site(flatEntityRaw.getIdBySite())
                    .build();

            dao.save(flatEntity);
            return true;

        }

        return false;
    }

}
