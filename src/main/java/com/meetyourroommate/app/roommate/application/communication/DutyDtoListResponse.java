package com.meetyourroommate.app.roommate.application.communication;

import com.meetyourroommate.app.roommate.application.tranform.dto.DutyDto;
import com.meetyourroommate.app.shared.application.communication.BaseResponse;

import java.util.List;

public class DutyDtoListResponse extends BaseResponse<List<DutyDto>> {

    public DutyDtoListResponse(String message) {
        super(message);
    }

    public DutyDtoListResponse(List<DutyDto> resource) {
        super(resource);
    }

    public DutyDtoListResponse(List<DutyDto> resource, String message) {
        super(resource, message);
    }
}
