package com.meetyourroommate.app.property.application.transform;

import com.meetyourroommate.app.property.application.transform.resources.PropertyFeatureResource;
import com.meetyourroommate.app.property.domain.entities.PropertyFeature;
import com.meetyourroommate.app.shared.application.transform.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

public class PropertyFeatureMapper implements Serializable {
    @Autowired
    private EnhancedModelMapper mapper;

    public PropertyFeature toEntity(PropertyFeatureResource model){
        return this.mapper.map(model, PropertyFeature.class);
    }

    public PropertyFeatureResource toResource(PropertyFeature entity){
        return this.mapper.map(entity, PropertyFeatureResource.class);
    }
}
