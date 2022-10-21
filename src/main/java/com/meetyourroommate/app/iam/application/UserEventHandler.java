package com.meetyourroommate.app.iam.application;

import com.meetyourroommate.app.iam.application.internal.events.UserSignedInEvent;
import com.meetyourroommate.app.iam.application.transform.resources.AuthenticationResource;

public interface UserEventHandler {
  AuthenticationResource on(UserSignedInEvent event);
}
