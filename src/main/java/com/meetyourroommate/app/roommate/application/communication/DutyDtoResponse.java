package com.meetyourroommate.app.roommate.application.communication;

import com.meetyourroommate.app.roommate.application.tranform.dto.DutyDto;
import com.meetyourroommate.app.shared.application.communication.BaseResponse;

public class DutyDtoResponse extends BaseResponse<DutyDto> {
    public DutyDtoResponse(String message) {
        super(message);
    }

    public DutyDtoResponse(DutyDto resource) {
        super(resource);
    }

    public DutyDtoResponse(DutyDto resource, String message) {
        super(resource, message);
    }
}
