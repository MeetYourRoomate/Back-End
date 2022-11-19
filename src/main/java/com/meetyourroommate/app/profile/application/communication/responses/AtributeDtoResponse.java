package com.meetyourroommate.app.profile.application.communication.responses;

import com.meetyourroommate.app.profile.application.transform.resources.AtributeDto;
import com.meetyourroommate.app.profile.domain.entities.Atribute;
import com.meetyourroommate.app.shared.application.communication.BaseResponse;

public class AtributeDtoResponse extends BaseResponse<AtributeDto> {
    public AtributeDtoResponse(String message) {
        super(message);
    }

    public AtributeDtoResponse(AtributeDto resource) {
        super(resource);
    }
}
