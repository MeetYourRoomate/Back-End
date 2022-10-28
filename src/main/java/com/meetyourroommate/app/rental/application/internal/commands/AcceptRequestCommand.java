package com.meetyourroommate.app.rental.application.internal.commands;

import com.meetyourroommate.app.rental.domain.enumerate.RentalStatus;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class AcceptRequestCommand {
    @TargetAggregateIdentifier
    private final Long requestId;
    private final RentalStatus rentalStatus;

    public AcceptRequestCommand(Long requestId, RentalStatus rentalStatus) {
        this.requestId = requestId;
        this.rentalStatus = rentalStatus;
    }

    public RentalStatus getRentalStatus() {
        return rentalStatus;
    }
    public Long getRequestId(){
        return this.requestId;
    }
}
