package com.meetyourroommate.app.profile.application.transform;

import com.meetyourroommate.app.profile.application.transform.resources.AtributeResource;
import com.meetyourroommate.app.profile.domain.entities.Attribute;
import com.meetyourroommate.app.shared.application.transform.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

public class AtributeResourceMapper implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;

    public Attribute toEntity(AtributeResource model){
        return mapper.map(model, Attribute.class);
    }
    public AtributeResource toResource(Attribute attribute){
        return mapper.map(attribute, AtributeResource.class);
    }
}
