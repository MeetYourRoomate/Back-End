package com.meetyourroommate.app.roommate.application.communication;

import com.meetyourroommate.app.roommate.domain.entities.RoommateRequest;
import com.meetyourroommate.app.shared.application.communication.BaseResponse;

public class RoommateRequestResponse extends BaseResponse<RoommateRequest> {
    public RoommateRequestResponse(String message) {
        super(message);
    }

    public RoommateRequestResponse(RoommateRequest resource) {
        super(resource);
    }
}
