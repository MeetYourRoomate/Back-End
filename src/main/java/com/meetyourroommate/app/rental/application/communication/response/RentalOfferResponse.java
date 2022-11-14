package com.meetyourroommate.app.rental.application.communication.response;

import com.meetyourroommate.app.rental.domain.entities.RentalOffering;
import com.meetyourroommate.app.shared.application.communication.BaseResponse;

public class RentalOfferResponse extends BaseResponse<RentalOffering> {
    public RentalOfferResponse(String message) {
        super(message);
    }

    public RentalOfferResponse(RentalOffering resource) {
        super(resource);
    }
}
