package com.meetyourroommate.app.roommate.application.communication;

import com.meetyourroommate.app.roommate.domain.entities.Team;
import com.meetyourroommate.app.shared.application.communication.BaseResponse;

public class TeamResponse extends BaseResponse<Team> {
    public TeamResponse(String message) {
        super(message);
    }

    public TeamResponse(Team resource) {
        super(resource);
    }
}
