package com.meetyourroommate.app.property.interfaces.rest;

import com.meetyourroommate.app.property.domain.aggregates.Property;
import com.meetyourroommate.app.property.domain.entities.PropertyAsset;
import com.meetyourroommate.app.property.application.transform.resources.PropertyAssetResource;
import com.meetyourroommate.app.property.application.services.PropertyAssetService;
import com.meetyourroommate.app.property.application.services.PropertyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Optional;

@Tag(name = "Property Asset", description = "Create, read, update and delete properties assets")
@RestController
@RequestMapping("/api/v1")
public class PropertyAssetController {
    @Autowired
    private PropertyAssetService propertyAssetService;
    @Autowired
    private PropertyService propertyService;

    @Operation(summary = "Create new property asset", description = "Create propertyasset to property")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Created property assets", content = @Content(mediaType = "application/json"))
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
               return new ResponseEntity<>("Property not found",HttpStatus.NOT_FOUND);
            }
        }catch(Exception e){
           return new ResponseEntity<Exception>(e,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "List property asset", description = "List all property assets by a property id")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Lieted property assets", content = @Content(mediaType = "application/json"))
    })
    @GetMapping(path = "/property/{id}/propertyasset", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findByProperty(@PathParam("id") Long propertyId){
       try{
           Optional<Property> property = propertyService.findById(propertyId);
           if(property.isPresent()){
               List<PropertyAsset> propertyAssetList = propertyAssetService.findAllByProperty(property.get());
               return new ResponseEntity<List<PropertyAsset>>(propertyAssetList,HttpStatus.OK);
           }else{
               return new ResponseEntity<String>("Property Not found.",HttpStatus.NOT_FOUND);
           }

       }catch(Exception e){
           return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }

    @Operation(summary = "Get property asset", description = "List property assets by a property id and asset id")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Listed property assets", content = @Content(mediaType = "application/json"))
    })
    @GetMapping(path = "/property/{property_id}/propertyasset/{asset_id}")
    public ResponseEntity<?> findByPropertyAndId(@PathParam("property_id")Long propertyId, @PathParam("asset_id") Long assetId){
       try{
           Optional<Property> property = propertyService.findById(propertyId);
           if(property.isPresent()){
               Optional<PropertyAsset> propertyAsset = propertyAssetService.findByPropertyAndId(property.get(), assetId);
               if(propertyAsset.isPresent()){
                   return new ResponseEntity<PropertyAsset>(propertyAsset.get(), HttpStatus.OK);
               } else{
                   return new ResponseEntity<String>("Asset not found.", HttpStatus.NOT_FOUND);
               }
           }else{
               return new ResponseEntity<String>("Property not found.", HttpStatus.NOT_FOUND);
           }
       }catch(Exception e){
           return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }

    @Operation(summary = "Delete property asset", description = "Delete property assets by a property id and asset id")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Deleted property assets", content = @Content(mediaType = "application/json"))
    })
    @DeleteMapping(path = "/property/{property_id}/propertyasset/{asset_id}")
    public ResponseEntity<?> delete(@PathParam("property_id")Long propertyId, @PathParam("asset_id") Long assetId){
        try{
            Optional<Property> property = propertyService.findById(propertyId);
            if(property.isPresent()){
                propertyAssetService.deleteById(assetId);
                List<PropertyAsset> propertyAssetList = propertyAssetService.findAllByProperty(property.get());
                return new ResponseEntity<List<PropertyAsset>>(propertyAssetList, HttpStatus.OK);
            }else{
                return new ResponseEntity<String>("Property not found.", HttpStatus.NOT_FOUND);
            }
        }catch(Exception e){
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
