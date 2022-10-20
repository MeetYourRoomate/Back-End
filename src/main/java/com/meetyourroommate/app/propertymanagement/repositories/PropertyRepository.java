package com.meetyourroommate.app.propertymanagement.repositories;

import com.meetyourroommate.app.propertymanagement.domain.aggregates.Property;
import com.meetyourroommate.app.propertymanagement.domain.entities.PropertyAsset;
import com.meetyourroommate.app.propertymanagement.domain.valueobjects.PropertyId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {
}
