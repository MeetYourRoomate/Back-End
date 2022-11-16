package com.meetyourroommate.app.roommate.infrastructure.persistance.jpa;

import com.meetyourroommate.app.roommate.domain.entities.RoommateStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoommateStatusRepository extends JpaRepository<RoommateStatus, String> {
}
