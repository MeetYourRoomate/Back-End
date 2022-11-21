package com.meetyourroommate.app.roommate.infrastructure.persistance.jpa;

import com.meetyourroommate.app.roommate.domain.entities.Roommate;
import com.meetyourroommate.app.roommate.domain.entities.RoommateStatus;
import com.meetyourroommate.app.roommate.domain.entities.TeamRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoommateStatusRepository extends JpaRepository<RoommateStatus, String> {
    List<RoommateStatus> findRoommateStatusesByTeamRequest(TeamRequest teamRequest);
    Optional<RoommateStatus> findRoommateStatusByRoommateAndTeamRequest (Roommate roommate, TeamRequest teamRequest);
}
