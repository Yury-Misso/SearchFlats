package by.itacademy.parser_sale.dao.entity;


import by.itacademy.parser_sale.dao.entity.enumClasses.OfferType;

public class FlatEntityBuilder {
    private String uuid;
    private Long dtCreate;
    private Long dtUpdate;
    private OfferType offerType;
    private String description;
    private Integer area;
    private Integer price;
    private Integer floor;
    private Integer bedrooms;
    private String[] photoUrls;
    private String originalUrl;
    private String idBySite;

    public FlatEntityBuilder setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public FlatEntityBuilder setDt_create(Long dtCreate) {
        this.dtCreate = dtCreate;
        return this;
    }

    public FlatEntityBuilder setDt_update(Long dtUpdate) {
        this.dtUpdate = dtUpdate;
        return this;
    }

    public FlatEntityBuilder setOffer_type(OfferType offerType) {
        this.offerType = offerType;
        return this;
    }

    public FlatEntityBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public FlatEntityBuilder setArea(Integer area) {
        this.area = area;
        return this;
    }

    public FlatEntityBuilder setPrice(Integer price) {
        this.price = price;
        return this;
    }

    public FlatEntityBuilder setFloor(Integer floor) {
        this.floor = floor;
        return this;
    }

    public FlatEntityBuilder setBedrooms(Integer bedrooms) {
        this.bedrooms = bedrooms;
        return this;
    }

    public FlatEntityBuilder setPhoto_urls(String[] photoUrls) {
        this.photoUrls = photoUrls;
        return this;
    }

    public FlatEntityBuilder setOriginal_url(String originalUrl) {
        this.originalUrl = originalUrl;
        return this;
    }

    public FlatEntityBuilder setId_by_site(String idBySite) {
        this.idBySite = idBySite;
        return this;
    }

    public FlatEntity build() {
        return new FlatEntity(uuid, dtCreate, dtUpdate, offerType, description, area, price, floor, bedrooms, photoUrls, originalUrl, idBySite);
    }
}