package com.meetyourroommate.app.roommate.interfaces.rest;

import com.meetyourroommate.app.iam.application.services.UserService;
import com.meetyourroommate.app.iam.domain.entities.enums.Roles;
import com.meetyourroommate.app.profile.application.services.ProfileService;
import com.meetyourroommate.app.profile.domain.aggregates.Profile;
import com.meetyourroommate.app.profile.domain.enumerate.TeamStatus;
import com.meetyourroommate.app.roommate.application.communication.RoommateRequestListResponse;
import com.meetyourroommate.app.roommate.application.communication.RoommateRequestResponse;
import com.meetyourroommate.app.roommate.application.services.RoommateRequestService;
import com.meetyourroommate.app.roommate.application.services.RoommateService;
import com.meetyourroommate.app.roommate.application.services.TeamService;
import com.meetyourroommate.app.roommate.domain.entities.Roommate;
import com.meetyourroommate.app.roommate.domain.entities.RoommateRequest;
import com.meetyourroommate.app.roommate.domain.entities.Team;
import com.meetyourroommate.app.shared.domain.enumerate.Status;
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
import java.util.UUID;

@Tag(name = "Roommate request", description = "Create, read, update and delete roommate request")
@RestController
@RequestMapping("/api/v1")
public class RoommateRequestController {
    private RoommateRequestService roommateRequestService;
    private UserService userService;
    private ProfileService profileService;
    private TeamService teamService;
    private RoommateService roommateService;
    public RoommateRequestController(RoommateRequestService roommateRequestService, UserService userService, ProfileService profileService, TeamService teamService, RoommateService roommateService) {
        this.roommateRequestService = roommateRequestService;
        this.userService = userService;
        this.profileService = profileService;
        this.teamService = teamService;
        this.roommateService = roommateService;
    }

    @Operation(summary = "Create new roommmate request", description = "Create new request to be roommates")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Created roommate request")
    })
    @PostMapping(value = "/users/requestor/{requestor_id}/requested/{requested_id}/roommate/request", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RoommateRequestResponse> CreateRequest(
            @PathVariable(name = "requestor_id") String requestorId,
            @PathVariable(name = "requested_id") Long requestedId ){
        try{
            Optional<Profile> requestorProfile = profileService.findByUserId(requestorId);
            if(requestorProfile.isEmpty()){
                return new ResponseEntity<>(
                        new RoommateRequestResponse("User" + requestorId + " not found"),
                        HttpStatus.NOT_FOUND);
            }
            Optional<Profile> requestedProfile = profileService.findById(requestedId);
            if(requestedProfile.isEmpty()){
                return new ResponseEntity<>(
                        new RoommateRequestResponse("User " + requestedId + " not found"),
                        HttpStatus.NOT_FOUND);
            }
            if(!IsStudent(requestorProfile.get())){
                return new ResponseEntity<>(
                        new RoommateRequestResponse("User" + requestorId + "is not a student"),
                        HttpStatus.BAD_REQUEST);
            }
            if(!IsStudent(requestedProfile.get())){
                return new ResponseEntity<>(
                        new RoommateRequestResponse("User" + requestedId + "is not a student"),
                        HttpStatus.BAD_REQUEST);
            }
            RoommateRequest roommateRequest = new RoommateRequest();
            roommateRequest.setStudentRequestor(requestorProfile.get());
            roommateRequest.setStudentRequested(requestedProfile.get());
            return new ResponseEntity<>(
                    new RoommateRequestResponse(
                       roommateRequestService.save(roommateRequest)
                    ) , HttpStatus.OK);

        }catch (Exception e){
           return new ResponseEntity<>(
                   new RoommateRequestResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "List all roommate request made by user", description = "List all roommate request made by user id")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Listed request")
    })
    @GetMapping("/users/{id}/roommate/requested")
    public ResponseEntity<RoommateRequestListResponse> findAllRoommateRequestByStudentRequestor(@PathVariable("id") String id){
        try{
            Optional<Profile> profile = profileService.findByUserId(id);

            if(profile.isEmpty()){
                return new ResponseEntity<>(
                        new RoommateRequestListResponse("Profile Requestor not found."),
                        HttpStatus.NOT_FOUND);
            }

            List<RoommateRequest> roommateRequestList = roommateRequestService.findAllByStudentRequestor(profile.get());
            return new ResponseEntity<>(
                    new RoommateRequestListResponse(roommateRequestList), HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity<>(
                    new RoommateRequestListResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "List all roommate request assigned to user", description = "List all roommate request assigned to user")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Listed request")
    })
    @GetMapping(value = "/users/{id}/roommate/requestors", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RoommateRequestListResponse> findAllRoommateRequestByStudentRequested(@PathVariable("id") String id){
        try{
            Optional<Profile> profile = profileService.findByUserId(id);
            if(profile.isEmpty()){
                return new ResponseEntity<>(
                        new RoommateRequestListResponse("Profile Requestor not found."), HttpStatus.NOT_FOUND);
            }
            List<RoommateRequest> roommateRequestList = roommateRequestService.findAllByStudentRequested(profile.get());
            return new ResponseEntity<>(
                    new RoommateRequestListResponse(roommateRequestList), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(
                    new RoommateRequestListResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Accept the roommate request", description = "Accept the roommate request to create a team")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Accept roommate request")
    })
    @PostMapping(value = "/roommate/request/{id}/accept", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RoommateRequestResponse> acceptRoommateRequest(@PathVariable("id")Long id){
        try{
            Optional<RoommateRequest> roommateRequest = roommateRequestService.findById(id);
            if(roommateRequest.isEmpty()){
                return new ResponseEntity<>(new RoommateRequestResponse("Roommate request not found."),HttpStatus.NOT_FOUND);
            }
            if(roommateRequest.get().getStatus() == Status.ACCEPTED){
                return new ResponseEntity<>(
                        new RoommateRequestResponse("Roommate request is already accept"), HttpStatus.CONFLICT);
            }
            //Acepted request
            roommateRequest.get().setStatus(Status.ACCEPTED);

            //Create team
            Team newTeam = new Team();
            newTeam.setName("Team-" + UUID.randomUUID().toString());
            teamService.save(newTeam);
            //Update profile status
            Profile profileResquestor = profileService.save(
                    roommateRequest.get().getStudentRequestor().setTeamStatus(TeamStatus.ONTEAM));
            Profile profileResquested = profileService.save(
                    roommateRequest.get().getStudentRequested().setTeamStatus(TeamStatus.ONTEAM));
            //Create roommates
            Roommate roommateRequestor = new Roommate();
            roommateRequestor.setProfile(profileResquestor);
            roommateRequestor.setTeam(newTeam);
            roommateService.save(roommateRequestor);

            Roommate roommateRequested = new Roommate();
            roommateRequested.setProfile(profileResquested);
            roommateRequested.setTeam(newTeam);
            roommateService.save(roommateRequested);

            roommateRequestService.save(roommateRequest.get());

            return new ResponseEntity<>(new RoommateRequestResponse(roommateRequest.get()), HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity<>(new RoommateRequestResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @Operation(summary = "Accept the roommate request", description = "Accept the roommate request to create a team")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200",
                    description = "Accept roommate request")
    })
    @PostMapping(value = "/roommate/request/{id}/decline" , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RoommateRequestResponse> declineRoommateRequest(@PathVariable("id")Long id){
        try{
            Optional<RoommateRequest> roommateRequest = roommateRequestService.findById(id);
            if(roommateRequest.isEmpty()){
                return new ResponseEntity<>(
                        new RoommateRequestResponse("Roommate request not found."),
                        HttpStatus.NOT_FOUND);
            }
            if(roommateRequest.get().getStatus() == Status.ACCEPTED){
                return new ResponseEntity<>(
                        new RoommateRequestResponse("Roommate request is already accepted"),
                        HttpStatus.CONFLICT
                );
            }
            roommateRequest.get().setStatus(Status.DECLINED);
            return new ResponseEntity<>(
                    new RoommateRequestResponse(roommateRequestService.save(roommateRequest.get())),
                    HttpStatus.OK
            );

        }catch(Exception e){
            return new ResponseEntity<>(
                    new RoommateRequestResponse(e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }


    private boolean IsStudent(Profile userProfile){
        return userProfile.getUser().getRole().getName() == Roles.ROLE_USER_STUDENT;
    }
}