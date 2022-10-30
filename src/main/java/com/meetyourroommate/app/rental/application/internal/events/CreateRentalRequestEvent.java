package com.meetyourroommate.app.rental.application.internal.events;

public class CreateRentalRequestEvent {

    private final String requestId;

    private final String message;

    private final String userId;
    private final Long offerId;

    public CreateRentalRequestEvent(String requestId, String message, String userId, Long offerId) {
        this.requestId = requestId;
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

    public String getRequestId() {
        return requestId;
    }
}
