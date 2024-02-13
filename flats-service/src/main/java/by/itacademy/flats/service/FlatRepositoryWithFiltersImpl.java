package by.itacademy.flats.service;

import by.itacademy.flats.aop.Audit;
import by.itacademy.flats.aop.eaudit.EssenceType;
import by.itacademy.flats.aop.eaudit.Text;
import by.itacademy.flats.core.dto.FlatDTO;
import by.itacademy.flats.core.dto.FlatFilter;
import by.itacademy.flats.core.dto.FlatsPageDTO;
import by.itacademy.flats.repository.IFlatRepositoryWithFilters;
import by.itacademy.flats.repository.entity.FlatEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
public class FlatRepositoryWithFiltersImpl implements IFlatRepositoryWithFilters {

    private final Logger logger = LoggerFactory.getLogger(FlatRepositoryWithFiltersImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    private final ModelMapper modelMapper;

    public FlatRepositoryWithFiltersImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional(readOnly = true)
    @Audit(essenceType = EssenceType.USER, text = Text.FIND_FLATS)
    public Page<FlatDTO> getFlatsWithFilters(FlatFilter flatFilter, Pageable pageable) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<FlatEntity> query = criteriaBuilder.createQuery(FlatEntity.class);

        Root<FlatEntity> flatEntityRoot = query.from(FlatEntity.class);

        Predicate predicate = createPredicate(flatFilter, criteriaBuilder, flatEntityRoot);

        query.where(predicate);

        List<FlatEntity> resultList = entityManager.createQuery(query)
                .setFirstResult(pageable.getPageNumber() * pageable.getPageSize())
                .setMaxResults(pageable.getPageSize())
                .getResultList();

        List<FlatDTO> flatDTOList = resultList.stream().map(flatEntity -> modelMapper.map(flatEntity, FlatDTO.class))
                .toList();

        Long maxSearch = getMaxSearch(flatFilter, criteriaBuilder, entityManager);

        return new PageImpl<FlatDTO>(flatDTOList, pageable, maxSearch);

    }

    public FlatsPageDTO convrtToFlatsPageDTO(Page<FlatDTO> flatDTOS) {

        return new FlatsPageDTO()
                .setNumber(flatDTOS.getNumber())
                .setSize(flatDTOS.getSize())
                .setTotalPages(flatDTOS.getTotalPages())
                .setTotalElements(flatDTOS.getTotalElements())
                .setFirst(flatDTOS.isFirst())
                .setNumberOfElements(flatDTOS.getNumberOfElements())
                .setLast(flatDTOS.isLast())
                .setContent(flatDTOS.getContent());

    }

    private Predicate createPredicate(FlatFilter flatFilter, CriteriaBuilder criteriaBuilder, Root<FlatEntity> root) {

        List<Function<FlatFilter, Optional<Predicate>>> predicateSuppliers = Arrays.asList(

                flFilter -> Optional.ofNullable(flFilter.getPriceFrom()).map(var -> criteriaBuilder.greaterThanOrEqualTo(root.get("price"), var)),
                flFilter -> Optional.ofNullable(flFilter.getPriceTo()).map(var -> criteriaBuilder.lessThanOrEqualTo(root.get("price"), var)),

                flFilter -> Optional.ofNullable(flFilter.getBedroomsFrom()).map(var -> criteriaBuilder.greaterThanOrEqualTo(root.get("bedrooms"), var)),
                flFilter -> Optional.ofNullable(flFilter.getBedroomsTo()).map(var -> criteriaBuilder.lessThanOrEqualTo(root.get("bedrooms"), var)),

                flFilter -> Optional.ofNullable(flFilter.getAreaFrom()).map(var -> criteriaBuilder.greaterThanOrEqualTo(root.get("area"), var)),
                flFilter -> Optional.ofNullable(flFilter.getAreaTo()).map(var -> criteriaBuilder.lessThanOrEqualTo(root.get("area"), var)),

                flFilter -> Optional.ofNullable(flFilter.getFloors()).filter(floors -> floors.length > 0).map(var -> root.<Integer>get("floor").in((Object) var)),

                flFilter -> Optional.ofNullable(flFilter.getPhoto()).map(var -> var ? root.get("photo_urls").isNotNull() : criteriaBuilder.equal(root.get("photo_urls"), new ArrayList<>()))

        );

        List<Predicate> predicates = predicateSuppliers.stream().map(
                        supplier -> supplier.apply(flatFilter)
                )
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();

        return predicates.isEmpty() ? criteriaBuilder.conjunction() : criteriaBuilder.and(predicates.toArray(new Predicate[0]));

    }

    private Long getMaxSearch(FlatFilter flatFilter,
                              CriteriaBuilder criteriaBuilder,
                              EntityManager entityManager) {

        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        Root<FlatEntity> from = countQuery.from(FlatEntity.class);
        countQuery.select(criteriaBuilder.countDistinct(from));

        Predicate predicate = createPredicate(flatFilter, criteriaBuilder, from);

        countQuery.where(predicate);

        return entityManager.createQuery(countQuery).getSingleResult();

    }


}
