package com.meetyourroommate.app.roommate.interfaces.rest;

import com.meetyourroommate.app.roommate.application.communication.DutyDtoListResponse;
import com.meetyourroommate.app.roommate.application.communication.DutyDtoResponse;
import com.meetyourroommate.app.roommate.application.communication.RoommateListDtoResponse;
import com.meetyourroommate.app.roommate.application.services.DutyService;
import com.meetyourroommate.app.roommate.application.services.RoommateService;
import com.meetyourroommate.app.roommate.application.services.TeamService;
import com.meetyourroommate.app.roommate.application.tranform.DutyDtoMapper;
import com.meetyourroommate.app.roommate.application.tranform.DutyResourceMapper;
import com.meetyourroommate.app.roommate.application.tranform.RoommateDtoMapper;
import com.meetyourroommate.app.roommate.application.tranform.dto.DutyDto;
import com.meetyourroommate.app.roommate.application.tranform.resources.DutyResource;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class DutiesController {
    private final DutyService dutyService;

    private final TeamService teamService;
    private final RoommateService roommateService;

    private final DutyResourceMapper dutyResourceMapper;
    private final DutyDtoMapper dutyDtoMapper;
    private final RoommateDtoMapper roommateDtoMapper;

    public DutiesController(DutyService dutyService, DutyResourceMapper dutyResourceMapper, TeamService teamService, RoommateService roommateService, DutyDtoMapper dutyDtoMapper, RoommateDtoMapper roommateDtoMapper) {
        this.dutyService = dutyService;
        this.dutyResourceMapper = dutyResourceMapper;
        this.teamService = teamService;
        this.roommateService = roommateService;
        this.dutyDtoMapper = dutyDtoMapper;
        this.roommateDtoMapper = roommateDtoMapper;
    }

    @Operation(summary = "Create new duty", description = "Create new duty", tags = {"Teams"})
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Created new duty")
    })
    @PostMapping("/teams/{team_id}/duties")
    public ResponseEntity<DutyDtoResponse> createDuty(
            @RequestBody DutyResource dutyResource,
            @PathVariable("team_id") Long teamId ) {

        try{
            Optional<Team> team = teamService.findById(teamId);
            if(team.isEmpty()){
                return new ResponseEntity<>(
                        new DutyDtoResponse("Team not found."),
                        HttpStatus.NOT_FOUND
                );
            }
            Duty newDuty = dutyResourceMapper.toEntity(dutyResource);
            newDuty.setTeam(team.get());
            return new ResponseEntity<>(
                    new DutyDtoResponse(
                            dutyDtoMapper.toDto(dutyService.save(newDuty))
                    ),
                    HttpStatus.OK
            );
        }catch (Exception e){
            return new ResponseEntity<>(
                    new DutyDtoResponse(e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @Operation(summary = "Get all duties from team", description = "Get all duties from team by team id", tags = {"Teams"})
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Listed all duties from team")
    })
    @GetMapping("/teams/{id}/duties")
    public ResponseEntity<DutyDtoListResponse> getAllDutiesFromTeam(@PathVariable("id") Long teamId){
       try{
           Optional<Team> team = teamService.findById(teamId);
           if(team.isEmpty()){
               return new ResponseEntity<>(
                       new DutyDtoListResponse("Team not found."),
                       HttpStatus.NOT_FOUND
               );
           }
           List<DutyDto> dtos = dutyDtoMapper.toDtoList(team.get().getDuties());
           return new ResponseEntity<>(
                   new DutyDtoListResponse(dtos),
                   HttpStatus.OK
           );
       }catch (Exception e){
           return new ResponseEntity<>(
                   new DutyDtoListResponse(e.getMessage()),
                   HttpStatus.INTERNAL_SERVER_ERROR
           );
       }
    }


    @Tag(name = "Duties", description = "Create, read, update and delete Duties")
    @Operation(summary = "Assign roommate to duties", description = "Assign roommate to duties")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Assigned roommates")
    })
    @PutMapping("/duties/{id}")
    public ResponseEntity<DutyDtoResponse> assignRoommatesToDuty(
            @RequestBody List<Long> roommatesIds,
            @PathVariable("id") String dutyId){
        try{
            Optional<Duty> duty = dutyService.findById(dutyId);
            if(duty.isEmpty()){
                return new ResponseEntity<>(
                        new DutyDtoResponse("Duty not found."),
                        HttpStatus.NOT_FOUND
                );
            }
            for (Long roomateId: roommatesIds) {
                Optional<Roommate> roommate = roommateService.findById(roomateId);
                if(roommate.isEmpty()){
                    return new ResponseEntity<>(
                            new DutyDtoResponse("The roommate on id " + roomateId.toString() + " has not been found."),
                            HttpStatus.NOT_FOUND
                    );
                }
                if(duty.get().getRoommateList().contains(roommate.get())){
                    return new ResponseEntity<>(
                            new DutyDtoResponse("The roommate on id " + roomateId.toString() + " is already assigned to the duty."),
                            HttpStatus.CONFLICT
                    );
                }
                duty.get().getRoommateList().add(roommate.get());
            }

            return new ResponseEntity<>(
                    new DutyDtoResponse(
                            dutyDtoMapper.toDto(
                                    dutyService.save(duty.get())
                            )),
                    HttpStatus.OK
            );

        }catch (Exception e){
            return new ResponseEntity<>(
                    new DutyDtoResponse(e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }
    @Operation(summary = "Obtain available roommates for a duty", description = "Obtain available roommates for a duty", tags = {"Duties"})
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Listed roommates")
    })
    @GetMapping(value = "/duties/{id}/roommates/available", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RoommateListDtoResponse> getAllRoommateAvailableToTheDuty(@PathVariable("id") String dutyId){
        try{
            Optional<Duty> duty = dutyService.findById(dutyId);
            if(duty.isEmpty()){
                return new ResponseEntity<>(
                        new RoommateListDtoResponse("Duty not found."),
                        HttpStatus.NOT_FOUND
                );
            }
            Team team = duty.get().getTeam();
            duty.get().getRoommateList().forEach((roommate) -> {
                team.getRoommates().remove(roommate);
            });
            return new ResponseEntity<>(
                    new RoommateListDtoResponse(
                            roommateDtoMapper.toDtoList(
                                    team.getRoommates()
                            )
                    ),
                    HttpStatus.OK
            );
        }catch (Exception e){
            return new ResponseEntity<>(
                    new RoommateListDtoResponse(e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }
}
