package com.meetyourroommate.app.roommate.application.tranform;

import com.meetyourroommate.app.roommate.application.tranform.resources.TeamRequestResource;
import com.meetyourroommate.app.roommate.domain.entities.TeamRequest;
import com.meetyourroommate.app.shared.application.transform.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class TeamRequestMapper {
    @Autowired
    private EnhancedModelMapper mapper;

    public TeamRequest toEntity(TeamRequestResource model) {
        return this.mapper.map(model, TeamRequest.class);
    }

    public TeamRequestResource toResource(TeamRequest entity) {
        return this.mapper.map(entity, TeamRequestResource.class);

    }
}