package com.meetyourroommate.app.rental.interfaces.rest;

import com.meetyourroommate.app.profile.application.services.ProfileService;
import com.meetyourroommate.app.profile.domain.aggregates.Profile;
import com.meetyourroommate.app.property.application.transform.PropertyMapper;
import com.meetyourroommate.app.property.domain.aggregates.Property;
import com.meetyourroommate.app.property.application.services.PropertyService;
import com.meetyourroommate.app.rental.application.communication.response.RentalOfferListResponse;
import com.meetyourroommate.app.rental.application.communication.response.RentalOfferPageResponse;
import com.meetyourroommate.app.rental.application.communication.response.RentalOfferResponse;
import com.meetyourroommate.app.rental.application.transform.RentalOfferMapper;
import com.meetyourroommate.app.rental.application.transform.resources.RentalOfferResource;
import com.meetyourroommate.app.rental.domain.entities.RentalOffering;
import com.meetyourroommate.app.rental.application.transform.resources.RentalOfferingResource;
import com.meetyourroommate.app.rental.application.services.RentalOfferingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class RentalLifecycleController {
    private PropertyService propertyService;
    private ProfileService profileService;
    private RentalOfferingService rentalOfferingService;

    private PropertyMapper propertyMapper;
    private RentalOfferMapper rentalOfferMapper;

    public RentalLifecycleController(PropertyService propertyService, ProfileService profileService, RentalOfferingService rentalOfferingService, PropertyMapper propertyMapper, RentalOfferMapper rentalOfferMapper) {
        this.propertyService = propertyService;
        this.profileService = profileService;
        this.rentalOfferingService = rentalOfferingService;
        this.propertyMapper = propertyMapper;
        this.rentalOfferMapper = rentalOfferMapper;
    }

    @Operation(summary = "Create new rental offer with property id", description = "Create rental offer to property", tags = {"Property"})
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Created property assets")
    })
    @PostMapping(path = "/properties/{id}/rentaloffers", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RentalOfferResponse> save(
            @PathVariable(value = "id") Long property_id,
            @RequestBody RentalOfferingResource rentalOfferingResource){
        try{
            Optional<Property> property = propertyService.findById(property_id);
            if(property.isEmpty()){
                return new ResponseEntity<>(
                        new RentalOfferResponse("Property Not Found."),
                        HttpStatus.NOT_FOUND);
            }
            Optional<RentalOffering> rentalOffering = rentalOfferingService.findByProperty(property.get());
            if(rentalOffering.isPresent()){
                return new ResponseEntity<>(
                        new RentalOfferResponse("Rental Offer exist."),
                        HttpStatus.CONFLICT);
            }
            RentalOffering newRentalOffering = new RentalOffering(rentalOfferingResource.getLifecycle(),rentalOfferingResource.getAmount(),rentalOfferingResource.getConditions());
            newRentalOffering.setProperty(property.get());
            return new ResponseEntity<>(
                    new RentalOfferResponse(rentalOfferingService.save(newRentalOffering)),
                    HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(
                    new RentalOfferResponse(e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Tag(name = "Rental offer", description = "Create, read, update and delete rental offer")
    @Operation(summary = "List rentaloffering", description = "List all rental offering without pagination")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Listed all rental offering")
    })
    @GetMapping(path = "/rentaloffers/all" ,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RentalOfferListResponse> findAll(){
        try{
            List<RentalOffering> rentalOfferingList = rentalOfferingService.findAll();
            return new ResponseEntity<>(
                    new RentalOfferListResponse(rentalOfferingList),
                    HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(
                    new RentalOfferListResponse(e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @Tag(name = "Rental offer", description = "Create, read, update and delete rental offer")
    @Operation(summary = "List rentaloffering", description = "List all rental offering with pagination and field")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Listed all rental offering")
    })
    @GetMapping(path = "/rentaloffers",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RentalOfferPageResponse> findByQueryAndSort(@RequestParam int offset, @RequestParam int pagesize, @RequestParam String field, @RequestParam String order){
        try{
            Page<RentalOffering> rentalOfferingPage = rentalOfferingService.findByOffsetAndPageSizeAndField(offset, pagesize,field, order);
            return new ResponseEntity<>(
                    new RentalOfferPageResponse(rentalOfferingPage),
                    HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(
                    new RentalOfferPageResponse(e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Tag(name = "Rental offer", description = "Create, read, update and delete rental offer")
    @Operation(summary = "Get offer by id", description = "Get ofer by id")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Offer")
    })
    @GetMapping(value = "/rentaloffers/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RentalOfferResponse> findById(@PathVariable Long id){
       try{
           Optional<RentalOffering> rentalOffering = rentalOfferingService.findById(id);
           if(rentalOffering.isEmpty()){
              return new ResponseEntity<>(
                      new RentalOfferResponse("Offer not found."),
                      HttpStatus.NOT_FOUND);
           }
           return new ResponseEntity<>(
                   new RentalOfferResponse(rentalOffering.get()),
                   HttpStatus.OK);
       }catch(Exception e){
           return new ResponseEntity<>(
                   new RentalOfferResponse(e.getMessage()),
                   HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }

    @Operation(summary = "Get offer by user id", description = "Get offers by user id", tags = {"Users"})
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Listed offers")
    })
    @GetMapping(value = "/users/{id}/rental/offers", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RentalOfferListResponse>findByUserId(@PathVariable String id){
        try{
            Optional<Profile> profile = profileService.findByUserId(id);
            if(profile.isEmpty()){
                return new ResponseEntity<>(
                        new RentalOfferListResponse("Profile not found."),
                        HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(
                    new RentalOfferListResponse(rentalOfferingService.findAllByProperty_Profile(profile.get())),
                    HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(
                    new RentalOfferListResponse(e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Create rental offer", description = "Create complete rental offer and property", tags = {"Users"})
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Created offer")
    })
    @PostMapping(value = "/users/{id}/rental/offer", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RentalOfferResponse> createOfferWithProperty(@PathVariable(value = "id") String id, @RequestBody RentalOfferResource rentalOfferResource){
        try{
            Optional<Profile> profile = profileService.findByUserId(id);

            if(profile.isEmpty()){
                return new ResponseEntity<>(
                        new RentalOfferResponse("Profile not found."),
                        HttpStatus.NOT_FOUND);
            }
            Property newProperty = propertyService.save(
                    propertyMapper.toEntity(rentalOfferResource.getPropertyResource())
                            .setProfile(profile.get()));

            RentalOffering newRentalOffering = rentalOfferMapper.toEntity(rentalOfferResource.getRentalOfferingResource());

            newRentalOffering.setProperty(newProperty);

            return new ResponseEntity<>(
                    new RentalOfferResponse(rentalOfferingService.save(newRentalOffering)),
                    HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(
                    new RentalOfferResponse(e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
