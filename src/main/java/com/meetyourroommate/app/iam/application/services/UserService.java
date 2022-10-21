package com.meetyourroommate.app.iam.application.services;

import com.meetyourroommate.app.iam.domain.aggregates.Users;
import com.meetyourroommate.app.shared.application.services.CrudService;

public interface UserService  extends CrudService<Users, String> {
}
