package com.meetyourroommate.app.roommate.application.tranform.dto;

import com.meetyourroommate.app.profile.domain.aggregates.Profile;
import lombok.Data;

import javax.persistence.Id;

@Data
public class RoommateDto {
    private Long id;
    private Profile profile;
}
