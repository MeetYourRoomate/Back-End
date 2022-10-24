package com.meetyourroommate.app.rental.interfaces.rest;

import com.meetyourroommate.app.profile.application.services.ProfileService;
import com.meetyourroommate.app.profile.domain.aggregates.Profile;
import com.meetyourroommate.app.property.domain.aggregates.Property;
import com.meetyourroommate.app.property.application.services.PropertyService;
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

import javax.swing.text.html.Option;
import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Optional;

@Tag(name = "Rental offer", description = "Create, read, update and delete rental offer")
@RestController
@RequestMapping("/api/v1")
public class RentalLifecycleController {
    private PropertyService propertyService;
    private ProfileService profileService;
    private RentalOfferingService rentalOfferingService;

    public RentalLifecycleController(PropertyService propertyService, ProfileService profileService, RentalOfferingService rentalOfferingService) {
        this.propertyService = propertyService;
        this.profileService = profileService;
        this.rentalOfferingService = rentalOfferingService;
    }

    @Operation(summary = "Create new property asset", description = "Create propertyasset to property")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Created property assets", content = @Content(mediaType = "application/json"))
    })
    @PostMapping(path = "/property/{id}/rentaloffers", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> save(@PathParam("id") Long property_id, @RequestBody RentalOfferingResource rentalOfferingResource){
        try{
            Optional<Property> property = propertyService.findById(property_id);
            if(property.isEmpty()){
                return new ResponseEntity<String>("Property Not Found.",HttpStatus.NOT_FOUND);
            }
            Optional<RentalOffering> rentalOffering = rentalOfferingService.findByProperty(property.get());
            if(rentalOffering.isPresent()){
                return new ResponseEntity<String>("Rental Offer exist.",HttpStatus.CONFLICT);
            }
            RentalOffering newRentalOffering = new RentalOffering(rentalOfferingResource.getLifecycle(),rentalOfferingResource.getAmount(),rentalOfferingResource.getConditions());
            newRentalOffering.setProperty(property.get());
            return new ResponseEntity<RentalOffering>(rentalOfferingService.save(newRentalOffering), HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "List rentaloffering", description = "List all rental offering without pagination")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Listed all rental offering", content = @Content(mediaType = "application/json"))
    })
    @GetMapping(path = "/rentaloffers/all" ,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findAll(){
        try{
            List<RentalOffering> rentalOfferingList = rentalOfferingService.findAll();
            return new ResponseEntity<List<RentalOffering>>(rentalOfferingList, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @Operation(summary = "List rentaloffering", description = "List all rental offering with pagination and field")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Listed all rental offering", content = @Content(mediaType = "application/json"))
    })
    @GetMapping(path = "/rentaloffers",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findByQueryAndSort(@RequestParam int offset, @RequestParam int pagesize,@RequestParam String field, @RequestParam String order){
        try{
            Page<RentalOffering> rentalOfferingPage = rentalOfferingService.findByOffsetAndPageSizeAndField(offset, pagesize,field, order);
            return new ResponseEntity<Page<RentalOffering>>(rentalOfferingPage, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Get offer by id", description = "Get ofer by id")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Offer", content = @Content(mediaType = "application/json"))
    })
    @GetMapping(value = "/rentaloffers/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findById(@PathVariable Long id){
       try{
           Optional<RentalOffering> rentalOffering = rentalOfferingService.findById(id);
           if(rentalOffering.isEmpty()){
              return new ResponseEntity<>("Offer not found.", HttpStatus.NOT_FOUND);
           }
           return new ResponseEntity<>(rentalOffering.get(), HttpStatus.OK);
       }catch(Exception e){
           return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }

    @GetMapping(value = "/users/{id}/rental/offers", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?>findByUserId(@PathVariable String id){
        try{
            Optional<Profile> profile = profileService.findByUserId(id);
            if(profile.isEmpty()){
                return new ResponseEntity<>("Profile not found.", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(rentalOfferingService.findAllByProperty_Profile(profile.get()),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
