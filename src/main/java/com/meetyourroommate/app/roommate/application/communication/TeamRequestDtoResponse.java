package com.meetyourroommate.app.roommate.application.communication;

import com.meetyourroommate.app.roommate.application.tranform.dto.TeamRequestDto;
import com.meetyourroommate.app.shared.application.communication.BaseResponse;

public class TeamRequestDtoResponse extends BaseResponse<TeamRequestDto> {
    public TeamRequestDtoResponse(String message) {
        super(message);
    }

    public TeamRequestDtoResponse(TeamRequestDto resource) {
        super(resource);
    }
}
