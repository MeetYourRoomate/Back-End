package com.meetyourroommate.app.roommate.application.communication;

import com.meetyourroommate.app.roommate.domain.entities.Team;
import com.meetyourroommate.app.shared.application.communication.BaseResponse;

import java.util.List;

public class TeamListResponse extends BaseResponse<List<Team>> {
    public TeamListResponse(String message) {
        super(message);
    }

    public TeamListResponse(List<Team> resource) {
        super(resource);
    }
}
