package com.meetyourroommate.app.roommate.application.tranform;

import com.meetyourroommate.app.roommate.application.tranform.dto.DutyDto;
import com.meetyourroommate.app.roommate.domain.entities.Duty;
import com.meetyourroommate.app.shared.application.transform.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class DutyDtoMapper {
    @Autowired
    EnhancedModelMapper mapper;
    public Duty toEntity(DutyDto model) {
        return this.mapper.map(model, Duty.class);
    }

    public DutyDto toDto(Duty entity){
        return this.mapper.map(entity, DutyDto.class);
    }
}
