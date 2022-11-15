package com.meetyourroommate.app.roommate.application.communication;

import com.meetyourroommate.app.roommate.domain.entities.Roommate;
import com.meetyourroommate.app.shared.application.communication.BaseResponse;

public class RoommateResponse extends BaseResponse<Roommate> {
    public RoommateResponse(String message) {
        super(message);
    }

    public RoommateResponse(Roommate resource) {
        super(resource);
    }
}
