package com.meetyourroommate.app.rental.application.internal.events;

import com.meetyourroommate.app.shared.domain.enumerate.Status;

public class AcceptRequestEvent {
    private final String rentalRequestId;
    private final Long requestIdentifier;
    private final Status status;

    public AcceptRequestEvent(String rentalRequestId, Long requestIdentifier, Status status) {
        this.rentalRequestId = rentalRequestId;
        this.requestIdentifier = requestIdentifier;
        this.status = status;
    }
    public String getRentalRequestId(){
        return this.rentalRequestId;
    }

    public Status getStatus() {
        return status;
    }

    public Long getRequestIdentifier() {
        return requestIdentifier;
    }
}
