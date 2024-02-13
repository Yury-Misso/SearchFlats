package by.itacademy.parser_rent.core.DTO;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlatDTO {

    private UUID uuid;
    private String title;
    private String description;
    private String headline;
    private String createdAt;
    private String updatedAt;
    private String metroTime;
    private String metroTimeType;
    private String price;
    private String priceCurrency;
    private String pricePerM2;
    private String pricePerM2Max;
    private String pricePerPerson;
    private String priceMin;
    private String priceMax;
    private String priceChangeDirection;
    private String priceChangeDate;
    private String storeys;
    private String storey;
    private String rooms;
    private String[] contactPhones;
    private String[] images;
    private String areaTotal;
    private String areaLiving;
    private String areaMax;
    private String areaMin;
    private String areaLand;
    private String objectType;
    private String code;
    private String stateRegionName;
    private String stateDistrictName;
    private String townType;
    private String townName;
    private String streetName;
    private String address;
    private String contactName;
    private String agencyName;
    private String metroStationName;
    private String metroLineId;
    private String houseNumber;
    private String buildingNumber;
    private String paymentStatus;
    private String comments;
    private Boolean isFavorite;
    private String category;
    private Boolean has3dTour;
    private Boolean hasVideo;
    private UUID stateRegionUuid;
    private String numberOfBeds;
    private String directionName;
    private String townDistance;
    private String customSorting;
    private String specialComment;
    private UUID userUuid;
    private UUID agencyUuid;
    private Double[] location;
    private UUID townUuid;
    private String buildingYear;
    private String levels;
    private String roofMaterial;
    private String wallMaterial;
    private String heating;
    private String infrastructure;
    private String balconyType;
    private String houseType;
    private String furniture;
    private String areaKitchen;
    private String[] appliances;
    private String objectCategory;
    private String realEstateDevUuid;
    private String availableYear;
    private String availableQuarter;
    private String availableAlready;
    private String availableText;
    private String isSellingCompleted;
    private String __typename;
    private JsonNode priceRates;
    private String priceRatesPerM2;
    private String priceRatesPerM2Max;
    private String priceRatesPerPerson;
    private String priceRatesMin;
    private String priceRatesMax;


}
