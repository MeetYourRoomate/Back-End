package com.meetyourroommate.app.propertymanagement.domain.controllers;

import com.meetyourroommate.app.propertymanagement.domain.aggregates.Property;
import com.meetyourroommate.app.propertymanagement.domain.entities.PropertyAsset;
import com.meetyourroommate.app.propertymanagement.domain.resources.PropertyAssetResource;
import com.meetyourroommate.app.propertymanagement.domain.resources.PropertyResource;
import com.meetyourroommate.app.propertymanagement.domain.service.PropertyAssetService;
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

import javax.websocket.server.PathParam;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class PropertyAssetController {
    @Autowired
    private PropertyAssetService propertyAssetService;
    @Autowired
    private PropertyService propertyService;

    @Operation(summary = "Create new property asset", description = "Create propertyasset to property", tags = {"property asset"})
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Created property", content = @Content(mediaType = "application/json"))
    })
    @PostMapping(path = "/property/{id}/propertyasset", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> save(@PathParam("id") Long id, @RequestBody PropertyAssetResource entity) {
        try{
            Optional<Property> property = propertyService.findById(id);
            if(property.isPresent()){
                PropertyAsset propertyAsset = new PropertyAsset(entity.getUrlImage(),property.get());
                propertyAssetService.save(propertyAsset);
                return new ResponseEntity<PropertyAsset>(propertyAsset, HttpStatus.OK);
            }else{
               return new ResponseEntity<>("Property not found.",HttpStatus.NOT_FOUND);
            }
        }catch(Exception e){
           return new ResponseEntity<Exception>(e,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
