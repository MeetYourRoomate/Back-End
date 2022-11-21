package com.meetyourroommate.app.roommate.infrastructure.persistance.jpa;

import com.meetyourroommate.app.roommate.domain.entities.Duty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DutyRepository extends JpaRepository<Duty, String> {
}
