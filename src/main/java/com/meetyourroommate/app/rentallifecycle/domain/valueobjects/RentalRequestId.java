package com.meetyourroommate.app.rentallifecycle.domain.valueobjects;

import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import java.io.Serializable;

@Embeddable
public class RentalRequestId implements Serializable {
    @GeneratedValue
    private Long id;
}
