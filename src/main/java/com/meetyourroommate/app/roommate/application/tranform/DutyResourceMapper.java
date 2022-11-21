package com.meetyourroommate.app.roommate.application.tranform;

import com.meetyourroommate.app.roommate.application.tranform.resources.DutyResource;
import com.meetyourroommate.app.roommate.domain.entities.Duty;
import com.meetyourroommate.app.shared.application.transform.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class DutyResourceMapper {
    @Autowired
    EnhancedModelMapper mapper;

    public Duty toEntity(DutyResource model) {
        return this.mapper.map(model, Duty.class);
    }

    public DutyResource toResource(Duty entity){
       return this.mapper.map(entity, DutyResource.class);
    }
}
