package com.meetyourroommate.app.profile.application.communication.responses;

import com.meetyourroommate.app.profile.application.transform.resources.AttributeDto;
import com.meetyourroommate.app.shared.application.communication.BaseResponse;

public class AtributeDtoResponse extends BaseResponse<AttributeDto> {
    public AtributeDtoResponse(String message) {
        super(message);
    }

    public AtributeDtoResponse(AttributeDto resource) {
        super(resource);
    }
}
