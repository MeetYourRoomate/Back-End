package com.meetyourroommate.app.propertymanagement.domain.service.impl;

import com.meetyourroommate.app.propertymanagement.domain.aggregates.Property;
import com.meetyourroommate.app.propertymanagement.domain.repositories.PropertyRepository;
import com.meetyourroommate.app.propertymanagement.domain.service.PropertyService;
import com.meetyourroommate.app.propertymanagement.domain.valueobjects.PropertyId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PropertyServiceImpl implements PropertyService {
    @Autowired
    private PropertyRepository propertyRepository;
    @Override
    public Property save(Property property) throws Exception {
        Property newProperty = new Property(property.getDescription());
        return propertyRepository.save(newProperty);
    }

    @Override
    public List<Property> findAll() throws Exception {
        return propertyRepository.findAll();
    }

    @Override
    public Optional<Property> findById(PropertyId propertyId) throws Exception {
        return propertyRepository.findById(propertyId);
    }

    @Override
    public Property update(PropertyId propertyId, Property property) throws Exception {
        Optional<Property> findedProperty = findById(propertyId);
        if (findedProperty.isPresent()){
        }
        return null;
    }

    @Override
    public void deleteById(PropertyId propertyId) throws Exception {

    }
}
