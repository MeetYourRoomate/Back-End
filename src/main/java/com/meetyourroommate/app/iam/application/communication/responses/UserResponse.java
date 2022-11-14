package com.meetyourroommate.app.iam.application.communication.responses;

import com.meetyourroommate.app.iam.domain.aggregates.User;
import com.meetyourroommate.app.shared.application.communication.BaseResponse;

public class UserResponse extends BaseResponse<User> {
    public UserResponse(String message) {
        super(message);
    }

    public UserResponse(User resource) {
        super(resource);
    }
}
