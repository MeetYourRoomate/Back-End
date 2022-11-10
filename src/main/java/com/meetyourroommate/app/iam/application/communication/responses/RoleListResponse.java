package com.meetyourroommate.app.iam.application.communication.responses;

import com.meetyourroommate.app.iam.domain.entities.Role;
import com.meetyourroommate.app.shared.application.communication.BaseResponse;

import java.util.List;

public class RoleListResponse extends BaseResponse<List<Role>> {
    public RoleListResponse(String message) {
        super(message);
    }

    public RoleListResponse(List<Role> resource) {
        super(resource);
    }
}
