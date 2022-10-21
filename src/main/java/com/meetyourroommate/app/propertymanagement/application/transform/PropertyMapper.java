package com.meetyourroommate.app.propertymanagement.application.transform;

import com.meetyourroommate.app.profile.application.transform.resources.ProfileResource;
import com.meetyourroommate.app.propertymanagement.application.transform.resources.PropertyResource;
import com.meetyourroommate.app.propertymanagement.domain.aggregates.Property;
import com.meetyourroommate.app.shared.application.transform.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

public class PropertyMapper implements Serializable {

    @Autowired
    private EnhancedModelMapper mapper;


    public Property toEntity(PropertyResource model){
        return this.mapper.map(model, Property.class);
    }
    public PropertyResource toResource(Property entity){
       return this.mapper.map(entity, PropertyResource.class);
    }
}
