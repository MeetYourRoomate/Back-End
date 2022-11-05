package com.meetyourroommate.app.roommate.application.services;

import com.meetyourroommate.app.roommate.domain.entities.Roommate;
import com.meetyourroommate.app.roommate.domain.entities.Team;
import com.meetyourroommate.app.shared.application.services.CrudService;

import java.util.Optional;

public interface TeamService extends CrudService<Team, Long> {
    Optional<Team> getByRoommatesContains(Roommate roommate);
}
