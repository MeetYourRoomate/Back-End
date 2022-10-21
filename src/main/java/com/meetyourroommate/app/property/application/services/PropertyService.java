package com.meetyourroommate.app.property.application.services;

import com.meetyourroommate.app.profile.domain.aggregates.Profile;
import com.meetyourroommate.app.property.domain.aggregates.Property;
import com.meetyourroommate.app.shared.application.services.CrudService;

import java.util.List;

public interface PropertyService extends CrudService<Property, Long> {
    List<Property> findAllByProfile(Profile profile);
}
