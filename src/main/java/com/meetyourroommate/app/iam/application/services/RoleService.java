package com.meetyourroommate.app.iam.application.services;

import com.meetyourroommate.app.iam.domain.entities.Role;
import com.meetyourroommate.app.iam.domain.entities.enums.Roles;
import com.meetyourroommate.app.shared.application.services.CrudService;

import java.util.Optional;

public interface RoleService extends CrudService<Role, Long> {
    Optional<Role> findByName(Roles roleName);
}
