package com.meetyourroommate.app.rental.application.internal.commands;

import com.meetyourroommate.app.shared.domain.enumerate.Status;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class AcceptRequestCommand {
    @TargetAggregateIdentifier
    private final String requestId;

    private final Long requestIdentifier;
    private final Status status;

    public AcceptRequestCommand(String requestId, Long requestIdentifier, Status status) {
        this.requestId = requestId;
        this.requestIdentifier = requestIdentifier;
        this.status = status;
    }

    public String getRequestId(){
        return this.requestId;
    }

    public Status getStatus() {
        return status;
    }

    public Long getRequestIdentifier() {
        return requestIdentifier;
    }
}
