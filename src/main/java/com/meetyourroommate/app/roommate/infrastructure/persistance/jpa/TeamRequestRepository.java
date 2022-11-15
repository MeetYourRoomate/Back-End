package com.meetyourroommate.app.roommate.infrastructure.persistance.jpa;

import com.meetyourroommate.app.roommate.domain.entities.TeamRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRequestRepository extends JpaRepository<TeamRequest, String> {
}
