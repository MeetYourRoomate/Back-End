package com.meetyourroommate.app.rentallifecycle.domain.valueobjects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class Amount implements Serializable {

    @Column(name="price", updatable = false)
    private double price;
    @Column(name="currency", updatable = false)
    private String currency;
}
