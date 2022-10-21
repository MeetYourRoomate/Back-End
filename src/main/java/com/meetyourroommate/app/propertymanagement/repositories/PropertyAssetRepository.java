package com.meetyourroommate.app.propertymanagement.repositories;

import com.meetyourroommate.app.propertymanagement.domain.aggregates.Property;
import com.meetyourroommate.app.propertymanagement.domain.entities.PropertyAsset;
import com.meetyourroommate.app.propertymanagement.domain.valueobjects.PropertyAssetId;
import com.meetyourroommate.app.propertymanagement.domain.valueobjects.PropertyId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PropertyAssetRepository extends JpaRepository<PropertyAsset, Long> {
    List<PropertyAsset> findAllByPropertyid(Property propertyId);
    Optional<PropertyAsset> findByPropertyidAndId(Property property, Long id);
}
