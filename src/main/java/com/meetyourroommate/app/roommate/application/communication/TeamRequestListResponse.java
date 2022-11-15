package com.meetyourroommate.app.roommate.application.communication;

import com.meetyourroommate.app.roommate.domain.entities.TeamRequest;
import com.meetyourroommate.app.shared.application.communication.BaseResponse;

import java.util.List;

public class TeamRequestListResponse extends BaseResponse<List<TeamRequest>> {
    public TeamRequestListResponse(String message) {
        super(message);
    }

    public TeamRequestListResponse(List<TeamRequest> resource) {
        super(resource);
    }
}
