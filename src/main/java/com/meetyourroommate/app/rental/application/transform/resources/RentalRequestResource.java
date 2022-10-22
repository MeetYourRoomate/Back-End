package com.meetyourroommate.app.rental.application.transform.resources;

import lombok.Data;

@Data
public class RentalRequestResource {
    private String message;
    private String userId;
    private Long rentalOfferId;
}
