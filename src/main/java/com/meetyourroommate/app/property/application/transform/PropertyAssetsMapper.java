package com.meetyourroommate.app.property.application.transform;

import com.meetyourroommate.app.property.application.transform.resources.PropertyAssetResource;
import com.meetyourroommate.app.property.domain.entities.PropertyAsset;
import com.meetyourroommate.app.property.infrastructure.persistance.jpa.PropertyAssetRepository;
import com.meetyourroommate.app.shared.application.transform.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

public class PropertyAssetsMapper implements Serializable {

    @Autowired
    private EnhancedModelMapper mapper;


    public PropertyAsset toEntity(PropertyAssetResource model){
        return this.mapper.map(model, PropertyAsset.class);
    }
    public PropertyAssetResource toResource(PropertyAsset entity) {
        return this.mapper.map(entity, PropertyAssetResource.class);
    }
}
