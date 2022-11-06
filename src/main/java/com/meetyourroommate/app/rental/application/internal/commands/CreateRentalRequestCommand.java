package com.meetyourroommate.app.rental.application.internal.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class CreateRentalRequestCommand {

    @TargetAggregateIdentifier
    private final String id;

    private final String message;

    private final String userId;
    private final Long offerId;

    public CreateRentalRequestCommand(String requestId, String message, String userId, Long offerId) {
        this.id = requestId;
        this.message = message;
        this.userId = userId;
        this.offerId = offerId;
    }

    public Long getOfferId() {
        return offerId;
    }

    public String getUserId() {
        return userId;
    }

    public String getMessage() {
        return message;
    }
    public String getRequestId(){
        return id;
    }
}
