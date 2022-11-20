package com.meetyourroommate.app.roommate.application.tranform;

import com.meetyourroommate.app.roommate.application.tranform.dto.TeamRequestDto;
import com.meetyourroommate.app.roommate.domain.entities.TeamRequest;
import com.meetyourroommate.app.shared.application.transform.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class TeamRequestDtoMapper {

    @Autowired
    EnhancedModelMapper mapper;

    public TeamRequest toEntity (TeamRequestDto models){
        return mapper.map(models, TeamRequest.class);
    }

    public TeamRequestDto toDto (TeamRequest entity){
        return mapper.map(entity, TeamRequestDto.class);
    }
}
