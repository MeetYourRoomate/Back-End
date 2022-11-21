package com.meetyourroommate.app.roommate.application.tranform.resources;

import lombok.Data;

import java.util.Date;

@Data
public class DutyResource {
    public String title;
    public String description;
    public Date dateline;
}
