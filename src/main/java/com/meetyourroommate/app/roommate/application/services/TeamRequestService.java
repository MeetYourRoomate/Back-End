package com.meetyourroommate.app.roommate.application.services;

import com.meetyourroommate.app.roommate.domain.entities.Team;
import com.meetyourroommate.app.roommate.domain.entities.TeamRequest;
import com.meetyourroommate.app.shared.application.services.CrudService;

import java.util.List;

public interface TeamRequestService extends CrudService<TeamRequest, String> {
    List<TeamRequest> findAllByTeamRequested(Team team);
}
