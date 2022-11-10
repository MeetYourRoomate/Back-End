package com.meetyourroommate.app.rental.application.communication.response;

import com.meetyourroommate.app.rental.domain.entities.RentalOffering;
import com.meetyourroommate.app.shared.application.communication.BaseResponse;
import org.springframework.data.domain.Page;

public class RentalOfferPageResponse extends BaseResponse<Page<RentalOffering>> {
    public RentalOfferPageResponse(String message) {
        super(message);
    }

    public RentalOfferPageResponse(Page<RentalOffering> resource) {
        super(resource);
    }
}
