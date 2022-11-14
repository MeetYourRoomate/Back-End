package com.meetyourroommate.app.iam.application.communication.responses;

import com.meetyourroommate.app.iam.domain.entities.Role;
import com.meetyourroommate.app.shared.application.communication.BaseResponse;

public class RoleResponse extends BaseResponse<Role> {
    public RoleResponse(String message) {
        super(message);
    }

    public RoleResponse(Role resource) {
        super(resource);
    }
}
