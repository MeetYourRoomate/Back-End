package com.meetyourroommate.app.propertymanagement.domain.repositories;

import com.meetyourroommate.app.propertymanagement.domain.entities.PropertyAsset;
import com.meetyourroommate.app.propertymanagement.domain.valueobjects.PropertyAssetId;
import com.meetyourroommate.app.propertymanagement.domain.valueobjects.PropertyId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyAssetRepository extends JpaRepository<PropertyAsset, Long> {
}
