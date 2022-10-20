package com.meetyourroommate.app.propertymanagement.domain.controllers;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.meetyourroommate.app.propertymanagement.domain.aggregates.Property;
import com.meetyourroommate.app.propertymanagement.domain.resources.PropertyResource;
import com.meetyourroommate.app.propertymanagement.domain.service.PropertyService;
import com.meetyourroommate.app.propertymanagement.domain.valueobjects.PropertyId;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PropertyController {
    @Autowired
    private PropertyService propertyService;
	@Operation(summary = "Create new property", description = "Create property to lessor", tags = {"property"})
	@ApiResponses( value = {
		@ApiResponse(responseCode = "200", description = "Created property", content = @Content(mediaType = "application/json"))
	})
	@PostMapping(path = "/property", produces = MediaType.APPLICATION_JSON_VALUE)
    public Property save(@RequestBody PropertyResource entity) throws Exception{
        Property newProperty = new Property();
        newProperty.setDescription(entity.getDescription());
        return propertyService.save(newProperty);
    }

    @Operation(summary = "Delete property", description = "Delete property", tags = {"property"})
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Deleted property", content = @Content(mediaType = "application/json"))
    })
    @DeleteMapping(path = "/property/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> delete(@RequestParam("id") Long id) throws Exception{
        propertyService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Get properties", description = "Get properties", tags = {"property"})
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Properties", content = @Content(mediaType = "application/json"))
    })
    @GetMapping(path = "/property", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Property>> getALl(){
        try{
            List<Property> properties = propertyService.findAll();
            return new ResponseEntity<List<Property>>(properties, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
