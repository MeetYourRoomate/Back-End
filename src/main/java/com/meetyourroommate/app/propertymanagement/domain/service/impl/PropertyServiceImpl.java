package com.meetyourroommate.app.propertymanagement.domain.service.impl;

import com.meetyourroommate.app.propertymanagement.domain.aggregates.Property;
import com.meetyourroommate.app.propertymanagement.domain.service.PropertyService;
import com.meetyourroommate.app.propertymanagement.domain.valueobjects.PropertyId;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PropertyServiceImpl implements PropertyService {

    @Override
    public Property save(Property property) throws Exception {
        Property newProperty = new Property(property.getDescription());
        return newProperty;
    }

    @Override
    public List<Property> findAll() throws Exception {
        return null;
    }

    @Override
    public Optional<Property> findById(PropertyId propertyId) throws Exception {
        return Optional.empty();
    }

    @Override
    public Property update(PropertyId propertyId, Property property) throws Exception {
        return null;
    }

    @Override
    public void deleteById(PropertyId propertyId) throws Exception {

    }
}
