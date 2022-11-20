package com.meetyourroommate.app.roommate.interfaces.rest;

import com.meetyourroommate.app.iam.domain.entities.enums.Roles;
import com.meetyourroommate.app.profile.application.services.ProfileService;
import com.meetyourroommate.app.profile.domain.aggregates.Profile;
import com.meetyourroommate.app.roommate.application.communication.TeamRequestDtoResponse;
import com.meetyourroommate.app.roommate.application.communication.TeamRequestListResponse;
import com.meetyourroommate.app.roommate.application.communication.TeamRequestResponse;
import com.meetyourroommate.app.roommate.application.services.RoommateService;
import com.meetyourroommate.app.roommate.application.services.RoommateStatusService;
import com.meetyourroommate.app.roommate.application.services.TeamRequestService;
import com.meetyourroommate.app.roommate.application.services.TeamService;
import com.meetyourroommate.app.roommate.application.tranform.TeamMapper;
import com.meetyourroommate.app.roommate.application.tranform.TeamRequestDtoMapper;
import com.meetyourroommate.app.roommate.application.tranform.TeamRequestMapper;
import com.meetyourroommate.app.roommate.application.tranform.resources.TeamRequestResource;
import com.meetyourroommate.app.roommate.domain.entities.Roommate;
import com.meetyourroommate.app.roommate.domain.entities.RoommateStatus;
import com.meetyourroommate.app.roommate.domain.entities.Team;
import com.meetyourroommate.app.roommate.domain.entities.TeamRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class TeamRequestController {
    private TeamRequestService teamRequestService;
    private ProfileService profileService;
    private TeamService teamService;
    private RoommateStatusService roommateStatusService;
    private RoommateService roommateService;
    private TeamRequestMapper teamRequestMapper;
    private TeamMapper teamMapper;
    private TeamRequestDtoMapper teamRequestDtoMapper;


    public TeamRequestController(TeamRequestService teamRequestService, ProfileService profileService, TeamService teamService, RoommateStatusService roommateStatusService, RoommateService roommateService, TeamRequestMapper teamRequestMapper, TeamMapper teamMapper, TeamRequestDtoMapper teamRequestDtoMapper) {
        this.teamRequestService = teamRequestService;
        this.profileService = profileService;
        this.teamService = teamService;
        this.roommateStatusService = roommateStatusService;
        this.roommateService = roommateService;
        this.teamRequestMapper = teamRequestMapper;
        this.teamMapper = teamMapper;
        this.teamRequestDtoMapper = teamRequestDtoMapper;
    }

    @Operation(summary = "Create team request"
            , description = "Create a team request to join a team", tags = {"Users"})
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Created TeamRequest")
    })
    @PostMapping("/users/{user_id}/teams/{team_id}/request")
    public ResponseEntity<TeamRequestResponse> createTeamRequest(
            @PathVariable("user_id") String userId,
            @PathVariable("team_id") Long teamId
    ){
        try{
            Optional<Profile> profile = profileService.findByUserId(userId);
            if(profile.isEmpty()){
                return new ResponseEntity<>(
                        new TeamRequestResponse("Profile not found."),
                        HttpStatus.NOT_FOUND
                );
            }
            if(!IsStudent(profile.get())){
                return new ResponseEntity<>(
                        new TeamRequestResponse("The user is not a student."),
                        HttpStatus.CONFLICT
                );
            }
            Optional<Team> team = teamService.findById(teamId);
            if(team.isEmpty()){
                return new ResponseEntity<>(
                        new TeamRequestResponse("Team not found"),
                        HttpStatus.NOT_FOUND
                );
            }
            TeamRequest teamRequest = new TeamRequest();
            teamRequest.setStudentRequestor(profile.get());
            teamRequest.setTeamRequested(team.get());
            TeamRequest newTeamRequest = teamRequestService.save(teamRequest);

            team.get().getRoommates().forEach((roommate) -> {
                RoommateStatus roommateStatus = new RoommateStatus();
                roommateStatus.setRoommate(roommate);
                roommateStatus.setTeamRequest(newTeamRequest);
                try {
                    roommateStatusService.save(roommateStatus);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
            Optional<TeamRequest> teamRequestOptional = teamRequestService.findById(newTeamRequest.getId());
            return new ResponseEntity<>(
                    new TeamRequestResponse(teamRequestOptional.get()),
                    HttpStatus.OK
            );
        }catch (Exception e){
            return new ResponseEntity<>(
                    new TeamRequestResponse(e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @Operation(summary = "Delete team request"
            , description = "Delete team request by id", tags = {"Teams"})
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Request deleted")
    })
    @DeleteMapping("/teams/request/{request_id}")
    public ResponseEntity<TeamRequestResponse> deleteTeamRequest(@PathVariable("request_id") String id){
        try{
            Optional<TeamRequest> teamRequest = teamRequestService.findById(id);
            if(teamRequest.isEmpty()){
                return new ResponseEntity<>(
                        new TeamRequestResponse("Team Request not found."),
                        HttpStatus.NOT_FOUND
                );
            }
            teamRequestService.deleteById(id);
            return new ResponseEntity<>(
                    new TeamRequestResponse("Request with id: " + id + "it was deleted."),
                    HttpStatus.OK
            );

        }catch (Exception e){
            return new ResponseEntity<>(
                    new TeamRequestResponse(e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @Operation(summary = "List all team request by team"
            , description = "List all team request by team", tags = {"Teams"})
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Team request listed")
    })
    @GetMapping(value = "/teams/{id}/request", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TeamRequestListResponse> findAllByTeamRequested(@PathVariable("id") Long id){
        try{
            Optional<Team> team = teamService.findById(id);
            if(team.isEmpty()){
                return new ResponseEntity<>(
                        new TeamRequestListResponse("Team not found."),
                        HttpStatus.NOT_FOUND
                );
            }
            List<TeamRequest> teamRequestList = teamRequestService.findAllByTeamRequested(team.get());
            List<TeamRequestResource> teamRequestResources = new ArrayList<>();
            teamRequestList.forEach((teamRequest -> {
               TeamRequestResource teamRequestResource = teamRequestMapper.toResource(teamRequest);
               teamRequestResource.setTeam(teamMapper.toResource(teamRequest.getTeamRequested()));
               teamRequestResources.add(teamRequestResource);
            }));
            return new ResponseEntity<>(
                    new TeamRequestListResponse(teamRequestResources),
                    HttpStatus.OK
            );
        }catch (Exception e){
            return new ResponseEntity<>(
                    new TeamRequestListResponse(e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @PutMapping("/users/{user_id}/teams/requests/{team_request_id}/accept")
    public ResponseEntity<TeamRequestDtoResponse> acceptTeamRequestByRoommate(
            @PathVariable("user_id") String userId,
            @PathVariable("team_request_id") String teamRequestId
    ){
        try{
            Optional<Profile> profile = profileService.findByUserId(userId);
            if(profile.isEmpty()){
                return new ResponseEntity<>(
                        new TeamRequestDtoResponse("Profile not found."),
                        HttpStatus.NOT_FOUND
                );
            }
            Optional<Roommate> roommate = roommateService.getRoommateByProfile(profile.get());
            if(roommate.isEmpty()){
                return new ResponseEntity<>(
                        new TeamRequestDtoResponse("Roommate not found, Profile is not assigned to a team."),
                        HttpStatus.NOT_FOUND
                );
            }
            Optional<TeamRequest> teamRequest = teamRequestService.findById(teamRequestId);
            if(teamRequest.isEmpty()){
                return new ResponseEntity<>(
                        new TeamRequestDtoResponse("Team request not found."),
                        HttpStatus.NOT_FOUND
                );
            }
            Optional<RoommateStatus> roommateStatus = roommateStatusService
                    .findRoommateStatusByRoommateAndTeamRequest(roommate.get(), teamRequest.get());
            if(roommateStatus.isEmpty()){
                return new ResponseEntity<>(
                        new TeamRequestDtoResponse("Roommate Status not found."),
                        HttpStatus.NOT_FOUND
                );
            }
            return new ResponseEntity<>(
                    new TeamRequestDtoResponse(teamRequestDtoMapper.toDto(teamRequest.get())),
                    HttpStatus.OK
            );
        } catch (Exception e){
            return new ResponseEntity<>(
                    new TeamRequestDtoResponse(e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    private boolean IsStudent(Profile userProfile){
        return userProfile.getUser().getRole().getName() == Roles.ROLE_USER_STUDENT;
    }
}
