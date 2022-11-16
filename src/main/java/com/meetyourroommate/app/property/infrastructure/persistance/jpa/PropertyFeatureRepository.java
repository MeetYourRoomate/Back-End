package com.meetyourroommate.app.property.infrastructure.persistance.jpa;

import com.meetyourroommate.app.property.domain.entities.PropertyFeature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyFeatureRepository extends JpaRepository<PropertyFeature, String> {
}
