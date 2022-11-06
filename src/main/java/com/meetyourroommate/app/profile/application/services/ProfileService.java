package com.meetyourroommate.app.profile.application.services;

import com.meetyourroommate.app.iam.domain.aggregates.User;
import com.meetyourroommate.app.iam.domain.entities.Role;
import com.meetyourroommate.app.profile.domain.aggregates.Profile;
import com.meetyourroommate.app.profile.domain.enumerate.TeamStatus;
import com.meetyourroommate.app.shared.application.services.CrudService;

import java.util.List;
import java.util.Optional;


public interface ProfileService extends CrudService<Profile, Long> {
    Optional<Profile> findByUser(User user);
    Optional<Profile> findByUserId(String userId);

    List<Profile> findByTeamStatus(TeamStatus teamStatus);
    List<Profile> findAllByUser_RoleAndTeamStatus(Role role, TeamStatus teamStatus);

}
