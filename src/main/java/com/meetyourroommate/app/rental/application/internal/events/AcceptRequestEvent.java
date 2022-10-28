package com.meetyourroommate.app.rental.application.internal.events;

public class AcceptRequestEvent {
    private final Long rentalRequestId;

    public AcceptRequestEvent(Long rentalRequestId) {
        this.rentalRequestId = rentalRequestId;
    }
    public Long getRentalRequestId(){
        return this.rentalRequestId;
    }
}
