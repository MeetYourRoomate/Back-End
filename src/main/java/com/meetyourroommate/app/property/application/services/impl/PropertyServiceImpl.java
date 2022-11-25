package com.meetyourroommate.app.property.application.services.impl;

import com.meetyourroommate.app.profile.domain.aggregates.Profile;
import com.meetyourroommate.app.property.domain.aggregates.Property;
import com.meetyourroommate.app.property.infrastructure.persistance.jpa.PropertyRepository;
import com.meetyourroommate.app.property.application.services.PropertyService;
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
        return propertyRepository.save(property);
    }

    @Override
    public List<Property> findAll() throws Exception {
        return propertyRepository.findAll();
    }

    @Override
    public Optional<Property> findById(Long propertyId) throws Exception {
        return propertyRepository.findById(propertyId);
    }

    @Override
    public Property update(Long propertyId, Property property) throws Exception {
        Optional<Property> findedProperty = findById(propertyId);
        return findedProperty.get();
    }

    @Override
    public void deleteById(Long propertyId) throws Exception {
        propertyRepository.deleteById(propertyId);
    }

    @Override
    public List<Property> findAllByProfile(Profile profile) {
        return propertyRepository.findAllByProfile(profile);
    }

    @Override
    public Optional<Property> findByTitle(String title) {
        return propertyRepository.findByTitle(title);
    }
}
