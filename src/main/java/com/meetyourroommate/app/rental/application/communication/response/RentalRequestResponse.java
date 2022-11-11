package com.meetyourroommate.app.rental.application.communication.response;

import com.meetyourroommate.app.rental.domain.entities.RentalRequest;
import com.meetyourroommate.app.shared.application.communication.BaseResponse;

public class RentalRequestResponse extends BaseResponse<RentalRequest> {
    public RentalRequestResponse(String message) {
        super(message);
    }

    public RentalRequestResponse(RentalRequest resource) {
        super(resource);
    }
}
