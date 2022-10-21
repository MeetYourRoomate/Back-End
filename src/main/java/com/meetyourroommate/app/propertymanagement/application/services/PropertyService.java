package com.meetyourroommate.app.propertymanagement.application.services;

import com.meetyourroommate.app.propertymanagement.domain.aggregates.Property;
import com.meetyourroommate.app.shared.application.services.CrudService;

public interface PropertyService extends CrudService<Property, Long> {
}
