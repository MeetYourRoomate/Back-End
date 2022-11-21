package com.meetyourroommate.app.roommate.application.communication;

import com.meetyourroommate.app.roommate.application.tranform.dto.RoommateDto;
import com.meetyourroommate.app.shared.application.communication.BaseResponse;

import java.util.List;

public class RoommateListDtoResponse extends BaseResponse<List<RoommateDto>> {
    public RoommateListDtoResponse(String message) {
        super(message);
    }

    public RoommateListDtoResponse(List<RoommateDto> resource) {
        super(resource);
    }

    public RoommateListDtoResponse(List<RoommateDto> resource, String message) {
        super(resource, message);
    }
}
