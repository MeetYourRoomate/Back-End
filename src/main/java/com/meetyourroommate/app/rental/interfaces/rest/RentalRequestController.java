package com.meetyourroommate.app.rental.interfaces.rest;

import com.meetyourroommate.app.profile.application.services.ProfileService;
import com.meetyourroommate.app.profile.domain.aggregates.Profile;
import com.meetyourroommate.app.rental.application.communication.response.RentalRequestListResponse;
import com.meetyourroommate.app.rental.application.communication.response.RentalRequestResponse;
import com.meetyourroommate.app.rental.application.services.RentalOfferingService;
import com.meetyourroommate.app.rental.application.services.RentalRequestService;
import com.meetyourroommate.app.rental.application.transform.resources.RentalRequestResource;
import com.meetyourroommate.app.rental.domain.entities.RentalOffering;
import com.meetyourroommate.app.rental.domain.entities.RentalRequest;
import com.meetyourroommate.app.rental.domain.enumerate.RentalStatus;
import com.meetyourroommate.app.shared.domain.enumerate.Status;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
@Slf4j
public class RentalRequestController {

    private final RentalRequestService rentalRequestService;
    private final RentalOfferingService rentalOfferingService;
    private final ProfileService profileService;

    public RentalRequestController(RentalRequestService rentalRequestService, RentalOfferingService rentalOfferingService, ProfileService profileService) {
        this.rentalRequestService = rentalRequestService;
        this.rentalOfferingService = rentalOfferingService;
        this.profileService = profileService;
    }

    @Tag(name = "Rental Request", description = "Create, read, update and delete rental request")
    @Operation(summary = "Create new rental request", description = "Create new rental request to create rental object")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Created rental request")
    })
    @PostMapping(path = "/requests", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RentalRequestResponse> save(@RequestBody RentalRequestResource resource){
        try{
            Optional<Profile> profile = profileService.findByUserId(resource.getUserId());
            if(profile.isEmpty()){
                return new ResponseEntity<>(
                        new RentalRequestResponse("User not found."),
                        HttpStatus.NOT_FOUND);
            }
            Optional<RentalOffering> rentalOffering = rentalOfferingService.findById(resource.getRentalOfferId());
            if(rentalOffering.isEmpty()){
                return new ResponseEntity<>(
                        new RentalRequestResponse("Rental offer not found"),
                        HttpStatus.NOT_FOUND);
            }
            Optional<RentalRequest> optionalRentalRequest = rentalRequestService.findByProfileAndOffer(profile.get(), rentalOffering.get());
            if(optionalRentalRequest.isPresent()){
                return new ResponseEntity<>(
                        new RentalRequestResponse("Rental request already created"),
                        HttpStatus.CONFLICT);
            }
            RentalRequest rentalRequest  = new RentalRequest(profile.get(), rentalOffering.get(), resource.getMessage());
            return new ResponseEntity<>(
                    new RentalRequestResponse(rentalRequestService.save(rentalRequest)) ,
                    HttpStatus.OK);

        }catch(Exception e){
           return new ResponseEntity<>(
                   new RentalRequestResponse(e.getMessage()),
                   HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Find all rental request by offer", description = "Find all rental request by offer id", tags ={"Rental offer"})
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Listed rental request")
    })
    @GetMapping(path = "/rentaloffers/{id}/requests", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RentalRequestListResponse> findAllByOffer(@PathVariable Long id){
        try{
            Optional<RentalOffering> rentalOffering = rentalOfferingService.findById(id);
            if(rentalOffering.isEmpty()){
                return new ResponseEntity<>(
                        new RentalRequestListResponse("Rental Offer not found."),
                        HttpStatus.NOT_FOUND);
            }
            List<RentalRequest> rentalOfferingList = rentalRequestService.findByRentalOffering(rentalOffering.get());
            return new ResponseEntity<>(
                    new RentalRequestListResponse(rentalOfferingList),
                    HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity<>(
                    new RentalRequestListResponse(e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Find all rental request by user id", description = "Find all rental request by user id", tags = {"Users"})
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Listed rental request")
    })
    @GetMapping(path = "/users/{id}/requests", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RentalRequestListResponse> findAllByUser(@PathVariable String id){
        try{
            Optional<Profile> profile = profileService.findByUserId(id);
            if(profile.isEmpty()){
                return new ResponseEntity<>(
                        new RentalRequestListResponse("Profile not found."),
                        HttpStatus.NOT_FOUND);
            }
            List<RentalRequest> rentalOfferingList = rentalRequestService.findByProfile(profile.get());
            return new ResponseEntity<>(
                    new RentalRequestListResponse(rentalOfferingList),
                    HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity<>(
                    new RentalRequestListResponse(e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Tag(name = "Rental Request", description = "Create, read, update and delete rental request")
    @Operation(summary = "Accept rental request", description = "Accept rental request by request id")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Acepted rental request")
    })
    @PutMapping(path = "/requests/{id}/accept")
    public ResponseEntity<RentalRequestResponse> acceptRentalRequest(@PathVariable Long id){
       try{
           Optional<RentalRequest> rentalRequest = rentalRequestService.findById(id);
           if(rentalRequest.isEmpty()){
              return new ResponseEntity<>(
                      new RentalRequestResponse("Rental request not found"),
                      HttpStatus.NOT_FOUND);
           }
           rentalRequest.get().setStatus(Status.ACCEPTED);
           rentalRequest.get().getRentalOffering().setStatus(RentalStatus.BUSY);
           //TODO: add logic to delete other rental request and generate the rental object
           return new ResponseEntity<>(
                   new RentalRequestResponse(rentalRequestService.save(rentalRequest.get())),
                   HttpStatus.OK);
       }catch(Exception e){
          return new ResponseEntity<>(
                  new RentalRequestResponse(e.getMessage()),
                  HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }

    @Tag(name = "Rental Request", description = "Create, read, update and delete rental request")
    @Operation(summary = "decline rental request", description = "decline rental request by request id")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Declined rental request")
    })
    @PutMapping(path = "/requests/{id}/decline")
    public ResponseEntity<RentalRequestResponse> declineRentalRequest(@PathVariable Long id){
        try{
            Optional<RentalRequest> rentalRequest = rentalRequestService.findById(id);
            if(rentalRequest.isEmpty()){
                return new ResponseEntity<>(
                        new RentalRequestResponse("Rental request not found"),
                        HttpStatus.NOT_FOUND);
            }
            rentalRequest.get().setStatus(Status.DECLINED);
            return new ResponseEntity<>(
                    new RentalRequestResponse(rentalRequestService.save(rentalRequest.get())),
                    HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(
                    new RentalRequestResponse(e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "List all rental request by user lessor", description = "List all rental request by lessor id", tags = {"Users"})
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Listed all rental request")
    })
    @GetMapping(path = "/users/lessor/{id}/request")
    public ResponseEntity<RentalRequestListResponse> listAllRentalRequestForUserLessor(
            @PathVariable("id") String id){
       try{
           Optional<Profile> profile = profileService.findByUserId(id);
           if(profile.isEmpty()){
               return new ResponseEntity<>(
                       new RentalRequestListResponse("User not found."),
                       HttpStatus.NOT_FOUND
               );
           }
           List<RentalRequest> rentalRequests = rentalRequestService.findByProfileLessor(profile.get());
           return new ResponseEntity<>(
                   new RentalRequestListResponse(rentalRequests),
                   HttpStatus.OK
           );
       } catch(Exception e){
           return new ResponseEntity<>(
                   new RentalRequestListResponse(e.getMessage()),
                   HttpStatus.INTERNAL_SERVER_ERROR
           );
       }
    }

    @Operation(summary = "Get rental request by profile and rental offer id", description = "Get rental request by profile id is user id and renatl offer id", tags = {"Rental Request"})
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Found Rental Request")
    })
    @GetMapping("/requests/student/{student_id}/rentals/offers/{rental_offer_id}")
    public ResponseEntity<RentalRequestResponse> getRentalRequestByStudentProfielAndRentalOffer(
            @PathVariable("student_id") String studentId,
            @PathVariable("rental_offer_id") Long rentalOfferId
    ){
        try{
            Optional<Profile> studentProfile = profileService.findByUserId(studentId);
            if(studentProfile.isEmpty()){
                return new ResponseEntity<>(
                        new RentalRequestResponse("Student profile not found."),
                        HttpStatus.NOT_FOUND
                );
            }
            Optional<RentalOffering> rentalOffer = rentalOfferingService.findById(rentalOfferId);
            if(rentalOffer.isEmpty()){
                return new ResponseEntity<>(
                        new RentalRequestResponse("Rental offer not found."),
                        HttpStatus.NOT_FOUND
                );
            }
            Optional<RentalRequest> rentalRequest = rentalRequestService
                    .findByProfileAndOffer(studentProfile.get(), rentalOffer.get());
            if(rentalRequest.isEmpty()){
                return new ResponseEntity<>(
                        new RentalRequestResponse("Rental request not found."),
                        HttpStatus.NOT_FOUND
                );
            }
            return new ResponseEntity<>(
                    new RentalRequestResponse(rentalRequest.get()),
                    HttpStatus.OK
            );
        }catch (Exception e){
            return new ResponseEntity<>(
                    new RentalRequestResponse(e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

}
