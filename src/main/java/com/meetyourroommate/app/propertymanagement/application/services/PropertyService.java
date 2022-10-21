package com.meetyourroommate.app.propertymanagement.application.services;

import com.meetyourroommate.app.profile.domain.aggregates.Profile;
import com.meetyourroommate.app.propertymanagement.domain.aggregates.Property;
import com.meetyourroommate.app.shared.application.services.CrudService;

import java.util.List;

public interface PropertyService extends CrudService<Property, Long> {
    List<Property> findAllByProfile(Profile profile);
}
