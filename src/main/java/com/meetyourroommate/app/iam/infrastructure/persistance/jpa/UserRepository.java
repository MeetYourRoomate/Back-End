package com.meetyourroommate.app.iam.infrastructure.persistance.jpa;

import com.meetyourroommate.app.iam.domain.aggregates.User;
import com.meetyourroommate.app.iam.domain.valueobjects.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByEmail(Email email);
}
