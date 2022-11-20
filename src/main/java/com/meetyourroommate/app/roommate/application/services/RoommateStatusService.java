package com.meetyourroommate.app.roommate.application.services;

import com.meetyourroommate.app.roommate.domain.entities.Roommate;
import com.meetyourroommate.app.roommate.domain.entities.RoommateStatus;
import com.meetyourroommate.app.roommate.domain.entities.TeamRequest;
import com.meetyourroommate.app.shared.application.services.CrudService;

import java.util.List;
import java.util.Optional;

public interface RoommateStatusService extends CrudService<RoommateStatus, String> {

    List<RoommateStatus> findRoommateStatusesByTeamRequest(TeamRequest teamRequest);

    Optional<RoommateStatus> findRoommateStatusByRoommateAndTeamRequest (Roommate roommate, TeamRequest teamRequest);
}
