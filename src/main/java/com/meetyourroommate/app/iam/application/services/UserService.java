package com.meetyourroommate.app.iam.application.services;

import com.meetyourroommate.app.iam.domain.aggregates.User;
import com.meetyourroommate.app.shared.application.services.CrudService;

public interface UserService  extends CrudService<User, Long> {
}
