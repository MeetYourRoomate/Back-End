package com.meetyourroommate.app.roommate.application.tranform.dto;

import com.meetyourroommate.app.roommate.domain.entities.Roommate;
import com.meetyourroommate.app.shared.domain.enumerate.Status;
import lombok.Data;

@Data
public class RoommateStatusDto {

    private Roommate roommate;
    private Status status;
}
