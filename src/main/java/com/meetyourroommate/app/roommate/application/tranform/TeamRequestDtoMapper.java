package com.meetyourroommate.app.roommate.application.tranform;

import com.meetyourroommate.app.roommate.application.tranform.dto.TeamRequestDto;
import com.meetyourroommate.app.roommate.domain.entities.TeamRequest;
import com.meetyourroommate.app.shared.application.transform.EnhancedModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;

public class TeamRequestDtoMapper {

    @Autowired
    EnhancedModelMapper mapper;

    public TeamRequest toEntity (TeamRequestDto models){
        return mapper.map(models, TeamRequest.class);
    }

    public TeamRequestDto toDto (TeamRequest entity){
        TypeMap<TeamRequest, TeamRequestDto> typeMap = this.mapper.getTypeMap(TeamRequest.class, TeamRequestDto.class);
        if(typeMap == null){
            TypeMap<TeamRequest, TeamRequestDto> propertyMapper = this.mapper.createTypeMap(TeamRequest.class, TeamRequestDto.class);
            propertyMapper.addMappings(mapp ->
            {
                mapp.map(src -> src.getRoommateStatuses(), TeamRequestDto::setRoommateStatuses);
            });
        }
        return mapper.map(entity, TeamRequestDto.class);
    }
}
