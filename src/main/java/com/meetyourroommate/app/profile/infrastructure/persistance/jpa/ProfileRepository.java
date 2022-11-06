package com.meetyourroommate.app.profile.infrastructure.persistance.jpa;

import com.meetyourroommate.app.iam.domain.aggregates.User;
import com.meetyourroommate.app.iam.domain.entities.Role;
import com.meetyourroommate.app.profile.domain.aggregates.Profile;
import com.meetyourroommate.app.profile.domain.enumerate.TeamStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
    Optional<Profile> findByUser(User user);
    List<Profile> findAllByTeamStatus(TeamStatus teamStatus);
    List<Profile> findAllByUser_RoleAndTeamStatus(Role role, TeamStatus teamStatus);
}
