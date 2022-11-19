package com.meetyourroommate.app.profile.application.communication.responses;

import com.meetyourroommate.app.profile.domain.aggregates.Profile;
import com.meetyourroommate.app.shared.application.communication.BaseResponse;

public class ProfileResponse extends BaseResponse<Profile> {
    public ProfileResponse(String message) {
        super(message);
    }

    public ProfileResponse(Profile resource) {
        super(resource);
    }
}
