package com.meetyourroommate.app.profile.application.transform;

import com.meetyourroommate.app.profile.application.transform.resources.AtributeResource;
import com.meetyourroommate.app.profile.domain.entities.Atribute;
import com.meetyourroommate.app.shared.application.transform.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

public class AtributeResourceMapper implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;

    public Atribute toEntity(AtributeResource model){
        return mapper.map(model, Atribute.class);
    }
    public AtributeResource toResource(Atribute atribute){
        return mapper.map(atribute, AtributeResource.class);
    }
}
