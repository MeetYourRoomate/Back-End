package com.meetyourroommate.app.propertymanagement.applitacation.service;

import com.meetyourroommate.app.propertymanagement.domain.aggregates.Property;
import com.meetyourroommate.app.propertymanagement.domain.entities.PropertyAsset;
import com.meetyourroommate.app.propertymanagement.domain.valueobjects.PropertyAssetId;
import com.meetyourroommate.app.shared.application.services.CrudService;

import java.util.List;
import java.util.Optional;

public interface PropertyAssetService extends CrudService<PropertyAsset, Long> {
    List<PropertyAsset> findAllByProperty(Property property);
    Optional<PropertyAsset> findByPropertyAndId(Property property, Long id);
}
