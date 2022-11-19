package com.meetyourroommate.app.profile.application.communication.responses;

import com.meetyourroommate.app.profile.application.transform.resources.ProfileResource;
import com.meetyourroommate.app.shared.application.communication.BaseResponse;

public class ProfileResourceResponse extends BaseResponse<ProfileResource> {
    public ProfileResourceResponse(String message) {
        super(message);
    }

    public ProfileResourceResponse(ProfileResource resource) {
        super(resource);
    }
}
