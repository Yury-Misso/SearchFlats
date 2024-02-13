package by.itacademy.flats.repository;

import by.itacademy.flats.core.dto.FlatDTO;
import by.itacademy.flats.core.dto.FlatFilter;
import by.itacademy.flats.core.dto.FlatsPageDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface IFlatRepositoryWithFilters {
    Page<FlatDTO> getFlatsWithFilters(FlatFilter flatFilter, Pageable pageable);

    FlatsPageDTO convrtToFlatsPageDTO(Page<FlatDTO> flatDTOS);
}
