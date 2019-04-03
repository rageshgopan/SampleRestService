package com.myRetail.model;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.math.BigDecimal;

@Table("product")
public class ProductTable {

    @PrimaryKey
    private long id;
    private BigDecimal price;
    private String currencyCode;

    public ProductTable(long id, BigDecimal price, String currencyCode) {
        this.id = id;
        this.price = price;
        this.currencyCode = currencyCode;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
