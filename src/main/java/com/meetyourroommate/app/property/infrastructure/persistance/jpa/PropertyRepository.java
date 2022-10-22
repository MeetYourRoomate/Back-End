package com.meetyourroommate.app.property.infrastructure.persistance.jpa;

import com.meetyourroommate.app.profile.domain.aggregates.Profile;
import com.meetyourroommate.app.property.domain.aggregates.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {
    List<Property> findAllByProfile(Profile profile);
}
