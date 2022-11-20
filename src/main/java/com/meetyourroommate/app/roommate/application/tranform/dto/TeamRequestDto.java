package com.meetyourroommate.app.roommate.application.tranform.dto;

import com.meetyourroommate.app.profile.domain.aggregates.Profile;
import com.meetyourroommate.app.roommate.domain.entities.RoommateStatus;
import com.meetyourroommate.app.shared.domain.enumerate.Status;
import lombok.Data;

import java.util.List;

@Data
public class TeamRequestDto {

    private String id;
    private Profile StudentRequestor;
    private Status status;
    private List<RoommateStatusDto> roommateStatuses;
}
