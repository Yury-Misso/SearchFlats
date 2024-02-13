package by.itacademy.flats.controller;

import by.itacademy.flats.core.dto.FlatDTO;
import by.itacademy.flats.core.dto.FlatFilter;
import by.itacademy.flats.core.dto.FlatsPageDTO;
import by.itacademy.flats.repository.IFlatRepositoryWithFilters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/api/v1")
public class FlatsController {

    private final Logger logger = LoggerFactory.getLogger(FlatsController.class);

    private final IFlatRepositoryWithFilters flatRepositoryWithFilters;

    public FlatsController(@Qualifier("flatRepositoryWithFiltersImpl") IFlatRepositoryWithFilters flatRepositoryWithFilters) {
        this.flatRepositoryWithFilters = flatRepositoryWithFilters;
    }

    @GetMapping("/flats")
    public ResponseEntity<FlatsPageDTO> getFlats(@RequestParam(name = "price_from", required = false) Integer priceFrom,
                                                 @RequestParam(name = "price_to", required = false) Integer priceTo,
                                                 @RequestParam(name = "bedrooms_from", required = false) Integer bedroomsFrom,
                                                 @RequestParam(name = "bedrooms_to", required = false) Integer bedroomsTo,
                                                 @RequestParam(name = "area_from", required = false) Integer areaFrom,
                                                 @RequestParam(name = "area_to", required = false) Integer areaTo,
                                                 @RequestParam(name = "floors", required = false) Integer[] floors,
                                                 @RequestParam(name = "photo", required = false) Boolean photo,
                                                 @RequestParam(name = "page", defaultValue = "0") Integer page,
                                                 @RequestParam(value = "size", defaultValue = "20") Integer size) {

        PageRequest pageRequest = PageRequest.of(page, size);

        FlatFilter flatFilter = new FlatFilter()
                .setPriceFrom(priceFrom)
                .setPriceTo(priceTo)
                .setBedroomsFrom(bedroomsFrom)
                .setBedroomsTo(bedroomsTo)
                .setAreaFrom(areaFrom)
                .setAreaTo(areaTo)
                .setFloors(floors)
                .setPhoto(photo);

        Page<FlatDTO> flatsWithFilters = flatRepositoryWithFilters.getFlatsWithFilters(flatFilter, pageRequest);

        FlatsPageDTO flatsPageDTO = new FlatsPageDTO()
                .setNumber(flatsWithFilters.getNumber())
                .setSize(flatsWithFilters.getSize())
                .setTotalPages(flatsWithFilters.getTotalPages())
                .setTotalElements(flatsWithFilters.getTotalElements())
                .setFirst(flatsWithFilters.isFirst())
                .setNumberOfElements(flatsWithFilters.getNumberOfElements())
                .setLast(flatsWithFilters.isLast())
                .setContent(flatsWithFilters.getContent());

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(flatsPageDTO);

    }

}
