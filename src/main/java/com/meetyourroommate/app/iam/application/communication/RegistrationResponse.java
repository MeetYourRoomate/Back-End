package com.meetyourroommate.app.iam.application.communication;

import com.meetyourroommate.app.iam.application.transform.resources.UserResource;
import com.meetyourroommate.app.shared.application.communication.BaseResponse;

public class RegistrationResponse extends BaseResponse<UserResource> {

  public RegistrationResponse(String message) {
    super(message);
  }
  public RegistrationResponse(UserResource resource) {
    super(resource);
  }

}
