package com.meetyourroommate.app.propertymanagement.service;

import com.meetyourroommate.app.propertymanagement.domain.aggregates.Property;
import com.meetyourroommate.app.propertymanagement.domain.valueobjects.PropertyId;
import com.meetyourroommate.app.shared.services.CrudService;

public interface PropertyService extends CrudService<Property, Long> {
}
