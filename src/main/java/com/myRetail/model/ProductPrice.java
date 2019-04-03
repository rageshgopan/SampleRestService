package com.myRetail.model;


import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;


public class ProductPrice {

    @JsonProperty(value = "value")
    @NotNull(message = "Price cannot be null")
    @Positive
    @Digits(integer = 6,fraction = 2)
    private BigDecimal price;


    @JsonProperty(value = "currency_code")
    @NotNull(message = "Currency Code cannot be null")
    @NotBlank
    private String currencyCode;

    public ProductPrice(BigDecimal price, String currencyCode) {
        this.price = price;
        this.currencyCode = currencyCode;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }
}
