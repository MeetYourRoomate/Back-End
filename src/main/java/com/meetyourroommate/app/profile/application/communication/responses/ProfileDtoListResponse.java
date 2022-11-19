package com.meetyourroommate.app.profile.application.communication.responses;

import com.meetyourroommate.app.profile.application.transform.resources.ProfileDto;
import com.meetyourroommate.app.shared.application.communication.BaseResponse;

import java.util.List;

public class ProfileDtoListResponse extends BaseResponse<List<ProfileDto>> {
    public ProfileDtoListResponse(String message) {
        super(message);
    }

    public ProfileDtoListResponse(List<ProfileDto> resource) {
        super(resource);
    }
}
