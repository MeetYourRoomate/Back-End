package com.meetyourroommate.app.profile.application.communication.responses;

import com.meetyourroommate.app.profile.application.transform.resources.AttributeDto;
import com.meetyourroommate.app.shared.application.communication.BaseResponse;

import java.util.List;

public class AtributeDtoListResponse extends BaseResponse<List<AttributeDto>> {
    public AtributeDtoListResponse(String message) {
        super(message);
    }

    public AtributeDtoListResponse(List<AttributeDto> resource) {
        super(resource);
    }
}
