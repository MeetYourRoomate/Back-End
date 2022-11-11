package com.meetyourroommate.app.rental.application.communication.response;

import com.meetyourroommate.app.rental.domain.entities.RentalOffering;
import com.meetyourroommate.app.shared.application.communication.BaseResponse;

import java.util.List;

public class RentalOfferListResponse extends BaseResponse<List<RentalOffering>> {
    public RentalOfferListResponse(String message) {
        super(message);
    }

    public RentalOfferListResponse(List<RentalOffering> resource) {
        super(resource);
    }
}
