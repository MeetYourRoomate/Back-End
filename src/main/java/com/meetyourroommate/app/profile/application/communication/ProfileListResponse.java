package com.meetyourroommate.app.profile.application.communication;

import com.meetyourroommate.app.profile.domain.aggregates.Profile;
import com.meetyourroommate.app.shared.application.communication.BaseResponse;

import java.util.List;

public class ProfileListResponse extends BaseResponse<List<Profile>> {
    public ProfileListResponse(String message) {
        super(message);
    }

    public ProfileListResponse(List<Profile> resource) {
        super(resource);
    }
}
