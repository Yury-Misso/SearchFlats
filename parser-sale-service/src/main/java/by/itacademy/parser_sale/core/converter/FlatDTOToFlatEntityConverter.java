package by.itacademy.parser_sale.core.converter;

import by.itacademy.parser_sale.core.DTO.FlatDTO;
import by.itacademy.parser_sale.dao.entity.FlatEntityRaw;
import by.itacademy.parser_sale.dao.entity.FlatEntityRawBuilder;
import by.itacademy.parser_sale.dao.entity.enumClasses.OfferType;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

public class FlatDTOToFlatEntityConverter {

    public static FlatEntityRaw convertDTOToEntity(FlatDTO flatDTO) throws JsonProcessingException {

        int currency = 0;
        if (flatDTO.getPriceRates() != null) {
            String[] split = flatDTO.getPriceRates().toString().replace("{", "").replace("}", "").split(",");

            String currencyItem = "933";

            for (String val : split) {
                String[] replace = val.replace("\"", "").split(":");
                if (replace[0].equalsIgnoreCase(currencyItem)) {
                    currency = Integer.parseInt(replace[1]);
                    break;
                }
            }
        }

        String title = flatDTO.getTitle();
        String description = flatDTO.getDescription();
        String comments = flatDTO.getComments();

        StringBuilder descriptionBuilder = new StringBuilder();
        descriptionBuilder.append(title != null ? title : "")
                .append(description != null ? description : "")
                .append(comments != null ? comments : "");
        String descriptionBuilderString = descriptionBuilder.isEmpty() ? "" : descriptionBuilder.toString();

        int area = flatDTO.getAreaTotal() != null ? (int) Double.parseDouble(flatDTO.getAreaTotal()) : 0;

        int floor = flatDTO.getStorey() != null ? (int) Double.parseDouble(flatDTO.getStorey()) : 0;

        int bedrooms = flatDTO.getRooms() != null ? (int) Double.parseDouble(flatDTO.getRooms()) : 0;

        return new FlatEntityRawBuilder()
                .setDt_create(OffsetDateTime.parse(flatDTO.getCreatedAt(), DateTimeFormatter.ISO_OFFSET_DATE_TIME).toInstant().toEpochMilli())
                .setDt_update(OffsetDateTime.parse(flatDTO.getUpdatedAt(), DateTimeFormatter.ISO_OFFSET_DATE_TIME).toInstant().toEpochMilli())
                .setOffer_type(OfferType.SALE)
                .setDescription(descriptionBuilderString)
                .setArea(area)
                .setPrice(currency)
                .setFloor(floor)
                .setBedrooms(bedrooms)
                .setPhoto_urls(flatDTO.getImages())
                .setOriginal_url(new StringBuilder().append("https://realt.by/sale-flats/object/").append(flatDTO.getCode()).append("/").toString())
                .setIdBySite(flatDTO.getCode())
                .build();
    }
}
