package com.meetyourroommate.app.property.infrastructure.persistance.jpa;

import com.meetyourroommate.app.property.domain.aggregates.Property;
import com.meetyourroommate.app.property.domain.entities.PropertyAsset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PropertyAssetRepository extends JpaRepository<PropertyAsset, Long> {
    List<PropertyAsset> findAllByPropertyid(Property propertyId);
    Optional<PropertyAsset> findByPropertyidAndId(Property property, Long id);
}
