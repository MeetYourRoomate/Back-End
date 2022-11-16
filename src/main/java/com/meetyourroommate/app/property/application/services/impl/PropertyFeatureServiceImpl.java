package com.meetyourroommate.app.property.application.services.impl;

import com.meetyourroommate.app.property.application.services.PropertyFeatureService;
import com.meetyourroommate.app.property.domain.entities.PropertyFeature;
import com.meetyourroommate.app.property.infrastructure.persistance.jpa.PropertyFeatureRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PropertyFeatureServiceImpl implements PropertyFeatureService {

    private PropertyFeatureRepository propertyFeatureRepository;

    public PropertyFeatureServiceImpl(PropertyFeatureRepository propertyFeatureRepository) {
        this.propertyFeatureRepository = propertyFeatureRepository;
    }

    @Override
    public PropertyFeature save(PropertyFeature propertyFeature) throws Exception {
        return propertyFeatureRepository.save(propertyFeature);
    }

    @Override
    public List<PropertyFeature> findAll() throws Exception {
        return propertyFeatureRepository.findAll();
    }

    @Override
    public Optional<PropertyFeature> findById(String id) throws Exception {
        return propertyFeatureRepository.findById(id);
    }

    @Override
    public PropertyFeature update(String s, PropertyFeature propertyFeature) throws Exception {
        return null;
    }

    @Override
    public void deleteById(String id) throws Exception {
        propertyFeatureRepository.deleteById(id);
    }
}
