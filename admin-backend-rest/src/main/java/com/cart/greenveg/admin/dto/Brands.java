package com.cart.greenveg.admin.dto;

public class Brands {

    private int id;
    private String brand_slug;
    private String brand_name;
    private int brand_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand_slug() {
        return brand_slug;
    }

    public void setBrand_slug(String brand_slug) {
        this.brand_slug = brand_slug;
    }

    public String getBrand_name() {
        return brand_name;
    }

    public void setBrand_name(String brand_name) {
        this.brand_name = brand_name;
    }

    public int getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(int brand_id) {
        this.brand_id = brand_id;
    }
}
