package com.meetyourroommate.app.profile.application.communication.responses;

import com.meetyourroommate.app.profile.application.transform.resources.ProfileDto;
import com.meetyourroommate.app.shared.application.communication.BaseResponse;

public class ProfileDtoResponse extends BaseResponse<ProfileDto> {
    public ProfileDtoResponse(String message) {
        super(message);
    }

    public ProfileDtoResponse(ProfileDto resource) {
        super(resource);
    }
}
