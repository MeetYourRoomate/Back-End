package com.meetyourroommate.app.roommate.application.tranform.dto;

import com.meetyourroommate.app.roommate.domain.entities.Roommate;
import com.meetyourroommate.app.shared.domain.enumerate.Status;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class DutyDto {
    public String id;
    public String title;
    public String description;
    public Status status;
    public Date dateline;
    public List<Roommate> roommateList;
}
