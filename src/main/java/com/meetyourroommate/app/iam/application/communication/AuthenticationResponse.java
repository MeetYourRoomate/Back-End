package com.meetyourroommate.app.iam.application.communication;

import com.meetyourroommate.app.iam.application.transform.resources.AuthenticationResource;
import com.meetyourroommate.app.shared.application.communication.BaseResponse;

public class AuthenticationResponse extends BaseResponse<AuthenticationResource> {

  public AuthenticationResponse(String message) {
    super(message);
  }

  public AuthenticationResponse(AuthenticationResource resource) {
    super(resource);
  }
  
}
