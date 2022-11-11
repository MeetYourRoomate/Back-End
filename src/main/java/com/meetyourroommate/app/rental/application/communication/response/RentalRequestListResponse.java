package com.meetyourroommate.app.rental.application.communication.response;

import com.meetyourroommate.app.rental.domain.entities.RentalRequest;
import com.meetyourroommate.app.shared.application.communication.BaseResponse;

import java.util.List;

public class RentalRequestListResponse extends BaseResponse<List<RentalRequest>> {
    public RentalRequestListResponse(String message) {
        super(message);
    }

    public RentalRequestListResponse(List<RentalRequest> resource) {
        super(resource);
    }
}
