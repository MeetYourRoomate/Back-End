package com.meetyourroommate.app.roommate.application.tranform;

import com.meetyourroommate.app.roommate.application.tranform.dto.RoommateDto;
import com.meetyourroommate.app.roommate.domain.entities.Roommate;
import com.meetyourroommate.app.shared.application.transform.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class RoommateDtoMapper {
    @Autowired
    EnhancedModelMapper mapper;

    public Roommate toEntity(RoommateDto model){
        return mapper.map(model, Roommate.class);
    }

    public RoommateDto toDto(Roommate entity){
        return mapper.map(entity, RoommateDto.class);
    }

    public List<RoommateDto> toDtoList(List<Roommate> entityList){
        List<RoommateDto> dtos = new ArrayList<>();
        entityList.forEach((entity) -> {
            dtos.add(mapper.map(entity, RoommateDto.class));
        });
        return dtos;
    }
}
