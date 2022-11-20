package com.meetyourroommate.app.roommate.interfaces.rest;

import com.meetyourroommate.app.roommate.application.services.DutyService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class DutiesController {
    private final DutyService dutyService;

    public DutiesController(DutyService dutyService) {
        this.dutyService = dutyService;
    }
}
