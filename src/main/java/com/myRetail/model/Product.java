package com.myRetail.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class Product  {

    @NotNull(message = "id cannot be null")
    private long id;
    @NotNull(message = "Product name cannot be null")
    private String name;
    @JsonProperty(value = "current_price")
    @Valid
    private ProductPrice currentPrice;

    public Product(long id, String name, ProductPrice currentPrice) {
        this.id = id;
        this.name = name;
        this.currentPrice = currentPrice;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProductPrice getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(ProductPrice currentPrice) {
        this.currentPrice = currentPrice;
    }
}
