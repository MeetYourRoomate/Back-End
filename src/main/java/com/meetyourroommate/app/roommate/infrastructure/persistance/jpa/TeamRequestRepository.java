package com.meetyourroommate.app.roommate.infrastructure.persistance.jpa;

import com.meetyourroommate.app.roommate.domain.entities.Team;
import com.meetyourroommate.app.roommate.domain.entities.TeamRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRequestRepository extends JpaRepository<TeamRequest, String> {
    List<TeamRequest> findAllByTeamRequested(Team team);
}
