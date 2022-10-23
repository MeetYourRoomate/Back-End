package com.meetyourroommate.app.rental.domain.valueobjects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class Amount implements Serializable {

    @Column(name="price", updatable = false)
    private double price;
    @Column(name="currency", updatable = false)
    private String currency;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
