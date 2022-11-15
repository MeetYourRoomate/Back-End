package com.meetyourroommate.app.roommate.interfaces.rest;

import com.meetyourroommate.app.profile.application.services.ProfileService;
import com.meetyourroommate.app.roommate.application.communication.RoommateResponse;
import com.meetyourroommate.app.roommate.application.services.RoommateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class RoommateController {
    private RoommateService roommateService;
    private ProfileService profileService;

    public RoommateController(RoommateService roommateService, ProfileService profileService) {
        this.roommateService = roommateService;
        this.profileService = profileService;
    }
}
