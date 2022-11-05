package com.meetyourroommate.app.roommate.interfaces.rest;

import com.meetyourroommate.app.iam.application.services.UserService;
import com.meetyourroommate.app.iam.domain.entities.enums.Roles;
import com.meetyourroommate.app.profile.application.services.ProfileService;
import com.meetyourroommate.app.profile.domain.aggregates.Profile;
import com.meetyourroommate.app.roommate.application.services.RoommateRequestService;
import com.meetyourroommate.app.roommate.domain.entities.Roommate;
import com.meetyourroommate.app.roommate.domain.entities.RoommateRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Tag(name = "Roommate request", description = "Create, read, update and delete roommate request")
@RestController
@RequestMapping("/api/v1")
public class RoommateRequestController {
    private RoommateRequestService roommateRequestService;
    private UserService userService;
    private ProfileService profileService;

    public RoommateRequestController(RoommateRequestService roommateRequestService, UserService userService, ProfileService profileService) {
        this.roommateRequestService = roommateRequestService;
        this.userService = userService;
        this.profileService = profileService;
    }

    @Operation(summary = "Create new roommmate request", description = "Create new request to be roommates")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Created roommate request", content = @Content(mediaType = "application/json"))
    })
    @PostMapping(value = "/users/requestor/{requestor_id}/requested/{requested_id}/roommate/request", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> CreateRequest(
            @PathVariable(name = "requestor_id") String requestorId,
            @PathVariable(name = "requested_id") String requestedId ){
        try{
            Optional<Profile> requestorProfile = profileService.findByUserId(requestorId);
            if(requestorProfile.isEmpty()){
                return new ResponseEntity<>("User " + requestorId + " not found",HttpStatus.NOT_FOUND);
            }
            Optional<Profile> requestedProfile = profileService.findByUserId(requestedId);
            if(requestedProfile.isEmpty()){
                return new ResponseEntity<>("User " + requestedId + " not found",HttpStatus.NOT_FOUND);
            }
            if(!IsStudent(requestorProfile.get())){
                return new ResponseEntity<>("User" + requestorId + "is not a student", HttpStatus.BAD_REQUEST);
            }
            if(!IsStudent(requestedProfile.get())){
                return new ResponseEntity<>("User" + requestedId + "is not a student", HttpStatus.BAD_REQUEST);
            }
            RoommateRequest roommateRequest = new RoommateRequest();
            roommateRequest.setStudentRequestor(requestorProfile.get());
            roommateRequest.setStudentRequested(requestedProfile.get());
            return new ResponseEntity<>(roommateRequestService.save(roommateRequest), HttpStatus.OK);

        }catch (Exception e){
           return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "List all roommate request made by user", description = "List all roommate request made by user id")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Listed request", content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/users/{id}/roommate/requested")
    public ResponseEntity<?> findAllRoommateRequestByStudentRequestor(@PathVariable("id") String id){
        try{
            Optional<Profile> profile = profileService.findByUserId(id);
            if(profile.isEmpty()){
                return new ResponseEntity<>("Profile Requestor not found.", HttpStatus.NOT_FOUND);
            }
            List<RoommateRequest> roommateRequestList = roommateRequestService.findAllByStudentRequestor(profile.get());
            return new ResponseEntity<>(roommateRequestList, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    private boolean IsStudent(Profile userProfile){
        return userProfile.getUser().getRole().getName() == Roles.ROLE_USER_STUDENT;
    }
}