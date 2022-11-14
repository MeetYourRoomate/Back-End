package com.meetyourroommate.app.profile.interfaces.rest;

import com.meetyourroommate.app.iam.application.services.RoleService;
import com.meetyourroommate.app.iam.application.services.UserService;
import com.meetyourroommate.app.iam.domain.aggregates.User;
import com.meetyourroommate.app.iam.domain.entities.Role;
import com.meetyourroommate.app.iam.domain.entities.enums.Roles;
import com.meetyourroommate.app.profile.application.communication.ProfileListResponse;
import com.meetyourroommate.app.profile.application.communication.ProfileResponse;
import com.meetyourroommate.app.profile.application.services.ProfileService;
import com.meetyourroommate.app.profile.application.transform.ProfileMapper;
import com.meetyourroommate.app.profile.application.transform.resources.ProfileResource;
import com.meetyourroommate.app.profile.domain.aggregates.Profile;
import com.meetyourroommate.app.profile.domain.enumerate.TeamStatus;
import com.meetyourroommate.app.property.application.services.PropertyService;
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

@RestController
@RequestMapping("/api/v1")
public class ProfileController {
    private ProfileService profileService;
    private UserService userService;
    private PropertyService propertyService;
    private ProfileMapper mapper;
    private final RoleService roleService;

    public ProfileController(ProfileService profileService, UserService userService, PropertyService propertyService, ProfileMapper mapper, RoleService roleService) {
        this.profileService = profileService;
        this.userService = userService;
        this.propertyService = propertyService;
        this.mapper = mapper;
        this.roleService = roleService;
    }

    @Tag(name = "Profile", description = "Create, read, update and delete profile")
    @Operation(summary = "Create profile", description = "Create new profile")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Created new profile")
    })
    @PostMapping(value = "/profiles", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProfileResponse> save(@RequestParam String userId, @RequestBody ProfileResource profileResource){
        try
        {
            Optional<User> user = userService.findById(userId);
            if(user.isEmpty()){
                return new ResponseEntity<>(new ProfileResponse("User not found."), HttpStatus.NOT_FOUND);
            }
            Optional<Profile> profile = profileService.findByUser(user.get());
            if(profile.isPresent()){
                return new ResponseEntity<>(new ProfileResponse("Profile Already exist."), HttpStatus.CONFLICT);
            }
            Profile newProfile = mapper.toEntity(profileResource);
            newProfile.setUser(user.get());
            return new ResponseEntity<>(new ProfileResponse(profileService.save(newProfile)), HttpStatus.OK);
        }catch(Exception e){
           return new ResponseEntity<>(new ProfileResponse(e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Tag(name = "Profile", description = "Create, read, update and delete profile")
    @Operation(summary = "List all profiles", description = "List all profiles")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "All Profiles")
    })
    @GetMapping(value = "/profiles", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProfileListResponse> getAllProfiles(){
        try{
            List<Profile> profileList = profileService.findAll();
            return new ResponseEntity<>(
                    new ProfileListResponse(profileList),
                    HttpStatus.OK
            );
        }catch(Exception e){
            return new ResponseEntity<>(
                    new ProfileListResponse(e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @Tag(name = "Profile", description = "Create, read, update and delete profile")
    @Operation(summary = "Get profile by profile id", description = "Get Profile by profile id")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Profile")
    })
    @GetMapping(value = "/profiles/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProfileResponse> getProfileById(@PathVariable("id") Long id){
        try{
            Optional<Profile> profile = profileService.findById(id);
            if(profile.isEmpty()){
                return new ResponseEntity<>(
                        new ProfileResponse("Profile not found."),
                        HttpStatus.NOT_FOUND
                );
            }
            return new ResponseEntity<>(
                    new ProfileResponse(profile.get()),
                    HttpStatus.OK
            );
        }catch(Exception e){
            return new ResponseEntity<>(
                    new ProfileResponse(e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @Operation(summary = "List all student profiles without team assigned", description = "List all profiles", tags = {"Users"})
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "All Profiles")
    })
    @GetMapping(value = "/users/{id}/profiles/without/team", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProfileListResponse> getAllProfilesWithoutTeam(@PathVariable("id") String id){
        try{
            Optional<Profile> profile = profileService.findByUserId(id);
            if(profile.isEmpty()){
                return new ResponseEntity<>(
                        new ProfileListResponse("User not found."),
                        HttpStatus.NOT_FOUND
                );
            }

            Optional<Role> role = roleService.findByName(Roles.ROLE_USER_STUDENT);
            if(role.isEmpty()){
                return new ResponseEntity<>(
                        new ProfileListResponse("The student role has not been created yet."),
                        HttpStatus.NOT_FOUND
                );
            }
            List<Profile> profileList = profileService.findAllByUser_RoleAndTeamStatus(role.get(),TeamStatus.WITHOUTTEAM);
            profileList.remove(profile.get());
            return new ResponseEntity<>(
                    new ProfileListResponse(profileList),
                    HttpStatus.OK
            );
        }catch (Exception e){
            return new ResponseEntity<>(
                    new ProfileListResponse(e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @Operation(summary = "Get profile by user id", description = "Get profile by user id", tags = "Users")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Profile")
    })
    @GetMapping(value = "/users/{id}/profiles")
    public ResponseEntity<ProfileResponse> getProfileByUserId(@PathVariable("id") String id){
        try{
            Optional<Profile> profile = profileService.findByUserId(id);
            if(profile.isEmpty()){
                return new ResponseEntity<>(
                        new ProfileResponse("Profile not found."),
                        HttpStatus.NOT_FOUND
                );
            }
            return new ResponseEntity<>(
                    new ProfileResponse(profile.get()),
                    HttpStatus.OK
            );
        }catch(Exception e){
            return new ResponseEntity<>(
                    new ProfileResponse(e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

}
