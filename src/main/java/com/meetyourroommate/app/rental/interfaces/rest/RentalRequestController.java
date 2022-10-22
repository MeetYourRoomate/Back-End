package com.meetyourroommate.app.rental.interfaces.rest;

import com.meetyourroommate.app.profile.application.services.ProfileService;
import com.meetyourroommate.app.profile.domain.aggregates.Profile;
import com.meetyourroommate.app.rental.application.services.RentalOfferingService;
import com.meetyourroommate.app.rental.application.services.RentalRequestService;
import com.meetyourroommate.app.rental.application.transform.resources.RentalRequestResource;
import com.meetyourroommate.app.rental.domain.entities.RentalOffering;
import com.meetyourroommate.app.rental.domain.entities.RentalRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class RentalRequestController {

    private RentalRequestService rentalRequestService;
    private RentalOfferingService rentalOfferingService;

    private ProfileService profileService;

    public RentalRequestController(RentalRequestService rentalRequestService, RentalOfferingService rentalOfferingService, ProfileService profileService) {
        this.rentalRequestService = rentalRequestService;
        this.rentalOfferingService = rentalOfferingService;
        this.profileService = profileService;
    }

    @Operation(summary = "Create new rental request", description = "Create new rental request to create rental object", tags = {"Rental Requesting"})
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Created rental request", content = @Content(mediaType = "application/json"))
    })
    @PostMapping(path = "/rental/request", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> save(@RequestBody RentalRequestResource resource){
        try{
            Optional<Profile> profile = profileService.findByUserId(resource.getUserId());
            if(profile.isEmpty()){
                return new ResponseEntity<>("Profile not found.", HttpStatus.NOT_FOUND);
            }
            Optional<RentalOffering> rentalOffering = rentalOfferingService.findById(resource.getRentalOfferId());
            if(rentalOffering.isEmpty()){
                return new ResponseEntity<>("RentalOffering not found.", HttpStatus.NOT_FOUND);
            }
            Optional<RentalRequest> optionalRentalRequest = rentalRequestService.findByProfileAndOffer(profile.get(), rentalOffering.get());
            if(optionalRentalRequest.isPresent()){
                return new ResponseEntity<>("Existing rental request",HttpStatus.CONFLICT);
            }
            RentalRequest rentalRequest  = new RentalRequest(profile.get(), rentalOffering.get(), resource.getMessage());
            return new ResponseEntity<>(rentalRequestService.save(rentalRequest), HttpStatus.OK);

        }catch(Exception e){
           return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(path = "/users/{id}/profiles/rentals/requests", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findAllByUser(){
       return new ResponseEntity<>(HttpStatus.OK);
    }
}
