package com.meetyourroommate.app.iam.application.services;

import com.meetyourroommate.app.iam.domain.aggregates.User;
import com.meetyourroommate.app.iam.domain.valueobjects.Email;
import com.meetyourroommate.app.shared.application.services.CrudService;

import java.util.Optional;

public interface UserService  extends CrudService<User, String> {
    Optional<User> findUserByEmail(Email email);
}
