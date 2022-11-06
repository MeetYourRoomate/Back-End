package com.meetyourroommate.app.property.interfaces.rest;

import com.meetyourroommate.app.property.application.communication.PropertyAssetsListResponse;
import com.meetyourroommate.app.property.application.transform.PropertyAssetsMapper;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Tag(name = "Property Asset", description = "Create, read, update and delete properties assets")
@RestController
@RequestMapping("/api/v1")
public class PropertyAssetController {
    private final PropertyAssetService propertyAssetService;
    private final PropertyService propertyService;

    private final PropertyAssetsMapper propertyAssetsMapper;

    public PropertyAssetController(
            PropertyAssetService propertyAssetService,
            PropertyService propertyService,
            PropertyAssetsMapper propertyAssetsMapper) {

        this.propertyAssetService = propertyAssetService;
        this.propertyService = propertyService;
        this.propertyAssetsMapper = propertyAssetsMapper;
    }


    @Operation(summary = "Create new property asset", description = "Create propertyasset to property")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Created property assets", content = @Content(mediaType = "application/json"))
    })
    @PostMapping(path = "/property/{id}/propertyasset", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> save(@PathVariable("id") Long id, @RequestBody PropertyAssetResource entity) {
        try{
            Optional<Property> property = propertyService.findById(id);
            if(property.isPresent()){
                PropertyAsset propertyAsset = new PropertyAsset(entity.getUrlImage(),property.get());
                propertyAssetService.save(propertyAsset);
                return new ResponseEntity<>(propertyAsset, HttpStatus.OK);
            }else{
               return new ResponseEntity<>("Property not found",HttpStatus.NOT_FOUND);
            }
        }catch(Exception e){
           return new ResponseEntity<>(e,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "List property asset", description = "List all property assets by a property id")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Lieted property assets", content = @Content(mediaType = "application/json"))
    })
    @GetMapping(path = "/property/{id}/propertyasset", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findByProperty(@PathVariable("id") Long propertyId){
       try{
           Optional<Property> property = propertyService.findById(propertyId);
           if(property.isPresent()){
               List<PropertyAsset> propertyAssetList = propertyAssetService.findAllByProperty(property.get());
               return new ResponseEntity<>(propertyAssetList,HttpStatus.OK);
           }else{
               return new ResponseEntity<>("Property Not found.",HttpStatus.NOT_FOUND);
           }

       }catch(Exception e){
           return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }

    @Operation(summary = "Get property asset", description = "List property assets by a property id and asset id")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Listed property assets", content = @Content(mediaType = "application/json"))
    })
    @GetMapping(path = "/property/{property_id}/propertyasset/{asset_id}")
    public ResponseEntity<?> findByPropertyAndId(@PathVariable("property_id")Long propertyId, @PathVariable("asset_id") Long assetId){
       try{
           Optional<Property> property = propertyService.findById(propertyId);
           if(property.isPresent()){
               Optional<PropertyAsset> propertyAsset = propertyAssetService.findByPropertyAndId(property.get(), assetId);
               if(propertyAsset.isPresent()){
                   return new ResponseEntity<>(propertyAsset.get(), HttpStatus.OK);
               } else{
                   return new ResponseEntity<>("Asset not found.", HttpStatus.NOT_FOUND);
               }
           }else{
               return new ResponseEntity<>("Property not found.", HttpStatus.NOT_FOUND);
           }
       }catch(Exception e){
           return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }

    @Operation(summary = "Delete property asset", description = "Delete property assets by a property id and asset id")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Deleted property assets", content = @Content(mediaType = "application/json"))
    })
    @DeleteMapping(path = "/property/{property_id}/propertyasset/{asset_id}")
    public ResponseEntity<?> delete(@PathVariable("property_id")Long propertyId, @PathVariable("asset_id") Long assetId){
        try{
            Optional<Property> property = propertyService.findById(propertyId);
            if(property.isPresent()){
                propertyAssetService.deleteById(assetId);
                List<PropertyAsset> propertyAssetList = propertyAssetService.findAllByProperty(property.get());
                return new ResponseEntity<>(propertyAssetList, HttpStatus.OK);
            }else{
                return new ResponseEntity<>("Property not found.", HttpStatus.NOT_FOUND);
            }
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Create a list of property assets", description = "Append a lista of assets to property asset list")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Assets list created")
    })
    @PostMapping(value = "/property/{property_id}/assets", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PropertyAssetsListResponse> createAssetsOfPropertyByList(
            @PathVariable("property_id") Long id,
            @RequestBody List<PropertyAssetResource> propertyAssetResourceList){
        try{

            Optional<Property> property = propertyService.findById(id);
            if(property.isEmpty()){
                return new ResponseEntity<>(
                        new PropertyAssetsListResponse("Property not found."),
                        HttpStatus.NOT_FOUND
                );
            }
            List<PropertyAsset> propertyAssetList = new ArrayList<>();

            propertyAssetResourceList.forEach(
                    propertyAssetResource ->
                    {
                        try {
                            PropertyAsset propertyAsset = propertyAssetsMapper
                                    .toEntity(propertyAssetResource)
                                    .setPropertyid(property.get());

                            propertyAssetList.add(propertyAsset);

                            propertyAssetService
                            .save(propertyAsset);

                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
            );

            return new ResponseEntity<>(
                    new PropertyAssetsListResponse(propertyAssetList),
                    HttpStatus.OK
            );

        }catch (Exception e){
           return new ResponseEntity<>(
                   new PropertyAssetsListResponse(e.getMessage()),
                   HttpStatus.INTERNAL_SERVER_ERROR
           );
        }
    }
}
