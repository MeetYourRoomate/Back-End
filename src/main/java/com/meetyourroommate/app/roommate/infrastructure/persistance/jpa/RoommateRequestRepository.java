package com.meetyourroommate.app.roommate.infrastructure.persistance.jpa;

import com.meetyourroommate.app.profile.domain.aggregates.Profile;
import com.meetyourroommate.app.roommate.domain.entities.RoommateRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoommateRequestRepository extends JpaRepository<RoommateRequest, Long> {
    List<RoommateRequest> findAllByStudentRequestor(Profile studentRequestor);
    List<RoommateRequest> findAllByStudentRequested(Profile studentRequested);
    Optional<RoommateRequest> findRoommateRequestByStudentRequestedAndStudentRequestor(Profile requested, Profile requestor);
}
