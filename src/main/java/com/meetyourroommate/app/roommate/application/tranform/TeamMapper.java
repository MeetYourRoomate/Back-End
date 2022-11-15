package com.meetyourroommate.app.roommate.application.tranform;

import com.meetyourroommate.app.roommate.application.tranform.resources.TeamResource;
import com.meetyourroommate.app.roommate.domain.entities.Team;
import com.meetyourroommate.app.shared.application.transform.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class TeamMapper {
    @Autowired
    private EnhancedModelMapper mapper;

    public Team toEntity(TeamResource model){
        return this.mapper.map(model, Team.class);
    }

    public TeamResource toResource(Team entity){
        return this.mapper.map(entity, TeamResource.class);
    }
}
