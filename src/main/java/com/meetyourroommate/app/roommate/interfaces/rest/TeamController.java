package com.meetyourroommate.app.roommate.interfaces.rest;

import com.meetyourroommate.app.profile.application.services.ProfileService;
import com.meetyourroommate.app.profile.domain.aggregates.Profile;
import com.meetyourroommate.app.property.domain.valueobjects.PropertyId;
import com.meetyourroommate.app.roommate.application.communication.TeamResponse;
import com.meetyourroommate.app.roommate.application.services.RoommateService;
import com.meetyourroommate.app.roommate.application.services.TeamService;
import com.meetyourroommate.app.roommate.domain.entities.Roommate;
import com.meetyourroommate.app.roommate.domain.entities.Team;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.text.html.Option;
import java.util.Optional;

@Tag(name = "Team", description = "Create, read, update and delete team")
@RestController
@RequestMapping("/api/v1")
public class TeamController {
    private TeamService teamService;
    private RoommateService roommateService;
    private ProfileService profileService;

    public TeamController(TeamService teamService, RoommateService roommateService, ProfileService profileService) {
        this.teamService = teamService;
        this.roommateService = roommateService;
        this.profileService = profileService;
    }

    @GetMapping("/users/{id}/team")
    public ResponseEntity<TeamResponse> getTeam(@PathVariable("id") String id){
        try{
            Optional<Profile> profile = profileService.findByUserId(id);
            if(profile.isEmpty()){
                return new ResponseEntity<>(
                        new TeamResponse("User not found."), HttpStatus.NOT_FOUND
                );
            }
            Optional<Roommate> roommate = roommateService.getRoommateByProfile(profile.get());
            if(roommate.isEmpty()){
                return new ResponseEntity<>(
                        new TeamResponse("Roommate not found."), HttpStatus.NOT_FOUND
                );
            }
            return new ResponseEntity<>(
                    new TeamResponse(roommate.get().getTeam()),
                    HttpStatus.OK);

        }catch(Exception e){
            return new ResponseEntity<>(new TeamResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
