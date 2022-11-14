package com.meetyourroommate.app.roommate.interfaces.rest;

import com.meetyourroommate.app.profile.application.services.ProfileService;
import com.meetyourroommate.app.profile.domain.aggregates.Profile;
import com.meetyourroommate.app.property.domain.valueobjects.PropertyId;
import com.meetyourroommate.app.roommate.application.communication.TeamListResponse;
import com.meetyourroommate.app.roommate.application.communication.TeamResponse;
import com.meetyourroommate.app.roommate.application.services.RoommateService;
import com.meetyourroommate.app.roommate.application.services.TeamService;
import com.meetyourroommate.app.roommate.domain.entities.Roommate;
import com.meetyourroommate.app.roommate.domain.entities.Team;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jdk.jshell.spi.ExecutionControlProvider;
import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

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

    @Operation(summary = "Decline the roommate request", description = "Decline the roommate request to create a team", tags = {"Users"})
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200",
                    description = "Declined roommate request")
    })
    @GetMapping("/users/{id}/teams")
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

    @Tag(name = "Teams", description = "Create, read, update and delete Teams")
    @Operation(summary = "List all teams", description = "List all teams")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200",
                    description = "Listed all teams")
    })
    @GetMapping(value = "/teams", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TeamListResponse> getAllTeams(){
        try{
            List<Team> teamList = teamService.findAll();
            return new ResponseEntity<>(
                    new TeamListResponse(teamList),
                    HttpStatus.OK
            );
        }catch (Exception e){
           return new ResponseEntity<>(
                   new TeamListResponse(e.getMessage()),
                   HttpStatus.INTERNAL_SERVER_ERROR
           );
        }
    }
}
