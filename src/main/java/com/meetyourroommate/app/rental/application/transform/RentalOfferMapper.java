package com.meetyourroommate.app.rental.application.transform;

import com.meetyourroommate.app.rental.application.transform.resources.RentalOfferingResource;
import com.meetyourroommate.app.rental.domain.entities.RentalOffering;
import com.meetyourroommate.app.shared.application.transform.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

public class RentalOfferMapper implements Serializable {

    @Autowired
    private EnhancedModelMapper mapper;

    public RentalOffering toEntity(RentalOfferingResource model){
        return this.mapper.map(model, RentalOffering.class);
    }
    public RentalOfferingResource toResource(RentalOffering entity){
        return this.mapper.map(entity, RentalOfferingResource.class);
    }
}
