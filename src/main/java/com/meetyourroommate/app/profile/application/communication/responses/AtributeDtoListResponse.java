package com.meetyourroommate.app.profile.application.communication.responses;

import com.meetyourroommate.app.profile.application.transform.resources.AtributeDto;
import com.meetyourroommate.app.profile.domain.entities.Atribute;
import com.meetyourroommate.app.shared.application.communication.BaseResponse;

import java.util.List;

public class AtributeDtoListResponse extends BaseResponse<List<AtributeDto>> {
    public AtributeDtoListResponse(String message) {
        super(message);
    }

    public AtributeDtoListResponse(List<AtributeDto> resource) {
        super(resource);
    }
}
