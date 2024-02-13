package by.itacademy.flats.core.dto;

import by.itacademy.flats.core.converter.StringArrayConverter;
import by.itacademy.flats.core.enums.OfferType;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlatDTO {

    private String uuid;
    private Long dt_create;
    private Long dt_update;
    private OfferType offer_type;
    private String description;
    private Integer area;
    private Integer price;
    private Integer floor;
    private Integer bedrooms;
    private String[] photo_urls;
    private String original_url;

}
