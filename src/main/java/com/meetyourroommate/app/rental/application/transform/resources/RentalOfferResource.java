package com.meetyourroommate.app.rental.application.transform.resources;

import com.meetyourroommate.app.property.application.transform.resources.PropertyResource;
import lombok.Data;

@Data
public class RentalOfferResource {
    private RentalOfferingResource rentalOfferingResource;
    private PropertyResource propertyResource;
}
