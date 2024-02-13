package by.itacademy.parser_rent.dao.entity;


import by.itacademy.parser_rent.dao.entity.enumClasses.OfferType;

import java.util.Arrays;

public class FlatEntityRaw {
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
    private String idBySite;

    public FlatEntityRaw() {
    }

    public FlatEntityRaw(Long dt_create,
                         Long dt_update,
                         OfferType offer_type,
                         String description,
                         Integer area,
                         Integer price,
                         Integer floor,
                         Integer bedrooms,
                         String[] photo_urls,
                         String original_url,
                         String idBySite) {
        this.dt_create = dt_create;
        this.dt_update = dt_update;
        this.offer_type = offer_type;
        this.description = description;
        this.area = area;
        this.price = price;
        this.floor = floor;
        this.bedrooms = bedrooms;
        this.photo_urls = photo_urls;
        this.original_url = original_url;
        this.idBySite = idBySite;
    }

    public Long getDt_create() {
        return dt_create;
    }

    public void setDt_create(Long dt_create) {
        this.dt_create = dt_create;
    }

    public Long getDt_update() {
        return dt_update;
    }

    public void setDt_update(Long dt_update) {
        this.dt_update = dt_update;
    }

    public OfferType getOffer_type() {
        return offer_type;
    }

    public void setOffer_type(OfferType offer_type) {
        this.offer_type = offer_type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public Integer getBedrooms() {
        return bedrooms;
    }

    public void setBedrooms(Integer bedrooms) {
        this.bedrooms = bedrooms;
    }

    public String[] getPhoto_urls() {
        return photo_urls;
    }

    public void setPhoto_urls(String[] photo_urls) {
        this.photo_urls = photo_urls;
    }

    public String getOriginal_url() {
        return original_url;
    }

    public void setOriginal_url(String original_url) {
        this.original_url = original_url;
    }

    public String getIdBySite() {
        return idBySite;
    }

    public void setIdBySite(String idBySite) {
        this.idBySite = idBySite;
    }

    @Override
    public String toString() {
        return "FlatEntity{ " +
                "dt_create= " + dt_create + "\n" +
                "dt_update= " + dt_update + "\n" +
                "offer_type= " + offer_type + "\n" +
                "description= " + description + "\n" +
                "area= " + area + "\n" +
                "price= " + price + "\n" +
                "floor= " + floor + "\n" +
                "bedrooms= " + bedrooms + "\n" +
                "photo_urls= " + Arrays.stream(photo_urls).map(p -> p + "\n").toList() + "\n" +
                "original_url= " + original_url + "\n" +
                "id_by_site= " + idBySite + " }";
    }
}
