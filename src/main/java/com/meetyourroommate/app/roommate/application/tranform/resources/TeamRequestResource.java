package com.meetyourroommate.app.roommate.application.tranform.resources;

import com.meetyourroommate.app.profile.domain.aggregates.Profile;
import com.meetyourroommate.app.roommate.domain.entities.RoommateStatus;
import com.meetyourroommate.app.shared.domain.enumerate.Status;
import lombok.Data;

import java.util.List;

@Data
public class TeamRequestResource {
    private String id;

    private Profile studentRequestor;
    private TeamResource team;
    private List<RoommateStatus> roommateStatuses;
    private Status status;
}
