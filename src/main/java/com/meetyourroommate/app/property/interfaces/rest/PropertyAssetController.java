package com.meetyourroommate.app.property.interfaces.rest;

import com.meetyourroommate.app.property.application.communication.responses.PropertyAssetsResponse;
import com.meetyourroommate.app.property.application.communication.responses.PropertyAssetsListResponse;
import com.meetyourroommate.app.property.application.transform.PropertyAssetsMapper;
import com.meetyourroommate.app.property.domain.aggregates.Property;
import com.meetyourroommate.app.property.domain.entities.PropertyAsset;
import com.meetyourroommate.app.property.application.transform.resources.PropertyAssetResource;
import com.meetyourroommate.app.property.application.services.PropertyAssetService;
import com.meetyourroommate.app.property.application.services.PropertyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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


    @Operation(summary = "Create new property asset", description = "Create propertyasset to property", tags = {"Property"})
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Created property assets")
    })
    @PostMapping(path = "/properties/{id}/propertyasset", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PropertyAssetsResponse> save(
            @PathVariable("id") Long id,
            @RequestBody PropertyAssetResource resource) {
        try{
            Optional<Property> property = propertyService.findById(id);
            if(property.isEmpty()){
                return new ResponseEntity<>(
                        new PropertyAssetsResponse("Property not found."),
                        HttpStatus.NOT_FOUND);
            }
            PropertyAsset propertyAsset = propertyAssetsMapper.toEntity(resource);
            return new ResponseEntity<>(
                    new PropertyAssetsResponse(propertyAssetService.save(propertyAsset)),
                    HttpStatus.OK
            );
        }catch(Exception e){
           return new ResponseEntity<>(
                   new PropertyAssetsResponse(e.getMessage()),
                   HttpStatus.INTERNAL_SERVER_ERROR
           );
        }
    }

    @Operation(summary = "List property asset", description = "List all property assets by a property id", tags = {"Property"})
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Lieted property assets")
    })
    @GetMapping(path = "/properties/{id}/propertyasset", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PropertyAssetsListResponse> findByProperty(
            @PathVariable("id") Long propertyId){
       try{
           Optional<Property> property = propertyService.findById(propertyId);
           if(property.isEmpty()){
               return new ResponseEntity<>(
                       new PropertyAssetsListResponse("Property not found."),
                       HttpStatus.NOT_FOUND
               );
           }
           List<PropertyAsset> propertyAssetList = propertyAssetService.findAllByProperty(property.get());
           return new ResponseEntity<>(
                   new PropertyAssetsListResponse(propertyAssetList),
                   HttpStatus.OK
           );
       }catch(Exception e){
           return new ResponseEntity<>(new PropertyAssetsListResponse(e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }

    @Operation(summary = "Get property asset", description = "List property assets by a property id and asset id", tags = {"Property"})
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Listed property assets")
    })
    @GetMapping(path = "/properties/{property_id}/propertyasset/{asset_id}")
    public ResponseEntity<PropertyAssetsResponse> findByPropertyAndId(@PathVariable("property_id")Long propertyId, @PathVariable("asset_id") Long assetId){
       try{
           Optional<Property> property = propertyService.findById(propertyId);
           if(property.isEmpty()){
               return new ResponseEntity<>(
                       new PropertyAssetsResponse("Property not found."),
                       HttpStatus.NOT_FOUND
               );
           }
           Optional<PropertyAsset> propertyAsset = propertyAssetService.findByPropertyAndId(property.get(), assetId);
           if(propertyAsset.isEmpty()){
               return new ResponseEntity<>(
                       new PropertyAssetsResponse("Asset not found."),
                       HttpStatus.NOT_FOUND
               );
           }
           return new ResponseEntity<>(
                   new PropertyAssetsResponse(propertyAsset.get()),
                   HttpStatus.OK
           );
       }catch(Exception e){
           return new ResponseEntity<>(
                   new PropertyAssetsResponse(e.getMessage())
                   ,HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }

    @Operation(summary = "Delete property asset", description = "Delete property assets by a property id and asset id", tags = {"Property"})
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Deleted property assets")
    })
    @DeleteMapping(path = "/properties/{property_id}/propertyasset/{asset_id}")
    public ResponseEntity<PropertyAssetsListResponse> delete(
            @PathVariable("property_id")Long propertyId,
            @PathVariable("asset_id") Long assetId){
        try{
            Optional<Property> property = propertyService.findById(propertyId);
            if(property.isEmpty()){
                return new ResponseEntity<>(
                        new PropertyAssetsListResponse("Property not found."),
                        HttpStatus.NOT_FOUND
                );
            }
            propertyAssetService.deleteById(assetId);
            List<PropertyAsset> propertyAssetList = propertyAssetService.findAllByProperty(property.get());
            return new ResponseEntity<>(
                    new PropertyAssetsListResponse(propertyAssetList),
                    HttpStatus.OK
            );
        }catch(Exception e){
            return new ResponseEntity<>(
                    new PropertyAssetsListResponse(e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Create a list of property assets", description = "Append a lista of assets to property asset list", tags = {"Property"})
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Assets list created")
    })
    @PostMapping(value = "/properties/{property_id}/assets", produces = MediaType.APPLICATION_JSON_VALUE)
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
