package com.meetyourroommate.app.roommate.interfaces.rest;

import com.meetyourroommate.app.roommate.application.services.TeamService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Team", description = "Create, read, update and delete team")
@RestController
@RequestMapping("/api/v1")
public class TeamController {
    private TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }
}
