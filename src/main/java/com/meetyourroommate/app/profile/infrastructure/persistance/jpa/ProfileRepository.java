package com.meetyourroommate.app.profile.infrastructure.persistance.jpa;

import com.meetyourroommate.app.iam.domain.aggregates.Users;
import com.meetyourroommate.app.profile.domain.aggregates.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
    Profile findByUser(Users user);
}
