package com.meetyourroommate.app.roommate.application.communication;

import com.meetyourroommate.app.roommate.domain.entities.TeamRequest;
import com.meetyourroommate.app.shared.application.communication.BaseResponse;

public class TeamRequestResponse extends BaseResponse<TeamRequest> {
    public TeamRequestResponse(String message) {
        super(message);
    }

    public TeamRequestResponse(TeamRequest resource) {
        super(resource);
    }
}
