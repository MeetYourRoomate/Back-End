package com.meetyourroommate.app.iam.application.internal;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meetyourroommate.app.iam.application.UserEventHandler;
import com.meetyourroommate.app.iam.application.internal.events.UserSignedInEvent;
import com.meetyourroommate.app.iam.application.transform.resources.AuthenticationResource;
import com.meetyourroommate.app.shared.application.transform.EnhancedModelMapper;

@Service
public class UserEventHandlerImpl implements UserEventHandler {

  @Autowired
  private EnhancedModelMapper mapper;

  @Override
  public AuthenticationResource on(UserSignedInEvent event) {
    AuthenticationResource resource = mapper.map(event, AuthenticationResource.class);
    resource.setRoles(new ArrayList<String>());
    resource.setPermissions(new ArrayList<String>());
    return resource;
  }

}
