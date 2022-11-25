package com.meetyourroommate.app.roommate.interfaces.rest;

import com.meetyourroommate.app.profile.application.services.ProfileService;
import com.meetyourroommate.app.profile.domain.aggregates.Profile;
import com.meetyourroommate.app.profile.domain.enumerate.TeamStatus;
import com.meetyourroommate.app.roommate.application.communication.RoommateListDtoResponse;
import com.meetyourroommate.app.roommate.application.communication.TeamListResponse;
import com.meetyourroommate.app.roommate.application.communication.TeamResponse;
import com.meetyourroommate.app.roommate.application.services.DutyService;
import com.meetyourroommate.app.roommate.application.services.RoommateService;
import com.meetyourroommate.app.roommate.application.services.TeamService;
import com.meetyourroommate.app.roommate.application.tranform.RoommateDtoMapper;
import com.meetyourroommate.app.roommate.domain.entities.Duty;
import com.meetyourroommate.app.roommate.domain.entities.Roommate;
import com.meetyourroommate.app.roommate.domain.entities.Team;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class TeamController {
    private TeamService teamService;
    private RoommateService roommateService;
    private DutyService dutyService;
    private ProfileService profileService;
    private final RoommateDtoMapper roommateDtoMapper;

    public TeamController(TeamService teamService, RoommateService roommateService, DutyService dutyService, ProfileService profileService, RoommateDtoMapper roommateDtoMapper) {
        this.teamService = teamService;
        this.roommateService = roommateService;
        this.dutyService = dutyService;
        this.profileService = profileService;
        this.roommateDtoMapper = roommateDtoMapper;
    }

    @Operation(summary = "Get team by student user id", description = "Get team by student user id", tags = {"Users"})
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200",
                    description = "Team")
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

    @Operation(summary = "List all roommates", description = "list all roommates on a team", tags = {"Teams"})
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200",
                    description = "Listed all roommates")
    })
    @GetMapping(value = "/teams/{id}/roommates", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RoommateListDtoResponse> getAllRommatesOnTeam(@PathVariable("id") Long id){
        try{
            Optional<Team> team = teamService.findById(id);
            if(team.isEmpty()){
                return new ResponseEntity<>(
                        new RoommateListDtoResponse("Team not found."),
                        HttpStatus.NOT_FOUND
                );
            }
            return new ResponseEntity<>(
                    new RoommateListDtoResponse(
                            roommateDtoMapper.toDtoList(
                                    team.get().getRoommates())),
                    HttpStatus.OK
            );

        }catch (Exception e){
            return new ResponseEntity<>(
                    new RoommateListDtoResponse(e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }
    @Operation(summary = "Get team by  id", description = "Get team by id", tags = {"Teams"})
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200",
                    description = "Team")
    })
    @GetMapping(value = "/teams/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TeamResponse> getTeamById(@PathVariable("id") Long id){
        try{
            Optional<Team> team = teamService.findById(id);
            if(team.isEmpty()){
                return new ResponseEntity<>(
                        new TeamResponse("Team not found."),
                        HttpStatus.NOT_FOUND
                );
            }
            return new ResponseEntity<>(
                    new TeamResponse(team.get()),
                    HttpStatus.OK);
        }catch (Exception e){
           return new ResponseEntity<>(
                   new TeamResponse(e.getMessage()),
                   HttpStatus.INTERNAL_SERVER_ERROR
           );
        }
    }

    @Operation(summary = "Delete team by id", description = "Delete team and give roommates equipment-free status", tags = {"Teams"})
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200",
                    description = "Team")
    })
    @DeleteMapping(value = "/teams/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TeamResponse> deleteTeamById(@PathVariable("id")Long teamId){
        try{
            Optional<Team> team = teamService.findById(teamId);
            if(team.isEmpty()){
                return new ResponseEntity<>(
                        new TeamResponse("Team not found."),
                        HttpStatus.NOT_FOUND
                );
            }
            List<Roommate> roommates = team.get().getRoommates();
            List<Duty> duties = team.get().getDuties();
            duties.forEach(duty -> {
                try {
                    dutyService.deleteById(duty.getId());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
            roommates.forEach((roommate) -> {
                try {
                    Profile roProfile = roommate.getProfile();
                    roProfile.setTeamStatus(TeamStatus.WITHOUTTEAM);
                    profileService.save(roProfile);
                    roommateService.deleteById(roommate.getId());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
            teamService.deleteById(teamId);
            return new ResponseEntity<>(
                    new TeamResponse("Equipment satisfactorily dissolved."),
                    HttpStatus.OK
            );
        }catch (Exception e){
            return new ResponseEntity<>(
                    new TeamResponse(e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }
}
