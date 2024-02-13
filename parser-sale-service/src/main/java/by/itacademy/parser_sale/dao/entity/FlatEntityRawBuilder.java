package by.itacademy.parser_sale.dao.entity;


import by.itacademy.parser_sale.dao.entity.enumClasses.OfferType;

public class FlatEntityRawBuilder {
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

    public FlatEntityRawBuilder setDt_create(Long dtCreate) {
        this.dtCreate = dtCreate;
        return this;
    }

    public FlatEntityRawBuilder setDt_update(Long dtUpdate) {
        this.dtUpdate = dtUpdate;
        return this;
    }

    public FlatEntityRawBuilder setOffer_type(OfferType offerType) {
        this.offerType = offerType;
        return this;
    }

    public FlatEntityRawBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public FlatEntityRawBuilder setArea(Integer area) {
        this.area = area;
        return this;
    }

    public FlatEntityRawBuilder setPrice(Integer price) {
        this.price = price;
        return this;
    }

    public FlatEntityRawBuilder setFloor(Integer floor) {
        this.floor = floor;
        return this;
    }

    public FlatEntityRawBuilder setBedrooms(Integer bedrooms) {
        this.bedrooms = bedrooms;
        return this;
    }

    public FlatEntityRawBuilder setPhoto_urls(String[] photoUrls) {
        this.photoUrls = photoUrls;
        return this;
    }

    public FlatEntityRawBuilder setOriginal_url(String originalUrl) {
        this.originalUrl = originalUrl;
        return this;
    }

    public FlatEntityRawBuilder setIdBySite(String idBySite) {
        this.idBySite = idBySite;
        return this;
    }

    public FlatEntityRaw build() {
        return new FlatEntityRaw(dtCreate, dtUpdate, offerType, description, area, price, floor, bedrooms, photoUrls, originalUrl, idBySite);
    }
}