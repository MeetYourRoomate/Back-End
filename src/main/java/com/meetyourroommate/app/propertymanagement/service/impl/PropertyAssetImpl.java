package com.meetyourroommate.app.propertymanagement.service.impl;

import com.meetyourroommate.app.propertymanagement.domain.aggregates.Property;
import com.meetyourroommate.app.propertymanagement.domain.entities.PropertyAsset;
import com.meetyourroommate.app.propertymanagement.repositories.PropertyAssetRepository;
import com.meetyourroommate.app.propertymanagement.service.PropertyAssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PropertyAssetImpl implements PropertyAssetService {
    @Autowired
    private PropertyAssetRepository propertyAssetRepository;
    @Override
    public PropertyAsset save(PropertyAsset propertyAsset) throws Exception {
        return propertyAssetRepository.save(propertyAsset);
    }

    @Override
    public List<PropertyAsset> findAll() throws Exception {
        return propertyAssetRepository.findAll();
    }

    @Override
    public Optional<PropertyAsset> findById(Long propertyAssetId) throws Exception {
        return propertyAssetRepository.findById(propertyAssetId);
    }

    @Override
    public PropertyAsset update(Long propertyAssetId, PropertyAsset propertyAsset) throws Exception {
        return null;
    }

    @Override
    public void deleteById(Long propertyAssetId) throws Exception {
        propertyAssetRepository.deleteById(propertyAssetId);
    }
    public List<PropertyAsset> findAllByProperty(Property property){
        return propertyAssetRepository.findAllByPropertyid(property);
    }
    public Optional<PropertyAsset> findByPropertyAndId(Property property, Long id){
        return propertyAssetRepository.findByPropertyidAndId(property,id);
    }
}
