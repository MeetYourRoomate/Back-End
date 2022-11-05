package com.meetyourroommate.app.roommate.application.communication;

import com.meetyourroommate.app.roommate.domain.entities.RoommateRequest;
import com.meetyourroommate.app.shared.application.communication.BaseResponse;

import java.util.List;

public class RoommateRequestListResponse extends BaseResponse<List<RoommateRequest>> {
    public RoommateRequestListResponse(String message) {
        super(message);
    }

    public RoommateRequestListResponse(List<RoommateRequest> resource) {
        super(resource);
    }
}
