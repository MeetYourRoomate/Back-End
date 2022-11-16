package com.meetyourroommate.app.property.interfaces.rest;

import com.meetyourroommate.app.property.application.communication.responses.PropertyFeatureListResponse;
import com.meetyourroommate.app.property.application.services.PropertyFeatureService;
import com.meetyourroommate.app.property.application.services.PropertyService;
import com.meetyourroommate.app.property.application.transform.PropertyFeatureMapper;
import com.meetyourroommate.app.property.application.transform.resources.PropertyFeatureResource;
import com.meetyourroommate.app.property.domain.aggregates.Property;
import com.meetyourroommate.app.property.domain.entities.PropertyFeature;
import com.meetyourroommate.app.property.domain.valueobjects.Feature;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class PropertyFeatureController {
    private PropertyFeatureService propertyFeatureService;
    private PropertyService propertyService;

    private PropertyFeatureMapper propertyFeatureMapper;

    public PropertyFeatureController(PropertyFeatureService propertyFeatureService, PropertyService propertyService) {
        this.propertyFeatureService = propertyFeatureService;
        this.propertyService = propertyService;
    }

    @Operation(summary = "Create a list of property features", description = "Append a list of features to property feature list", tags = {"Property"})
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Feature list created")
    })
    @PostMapping("/properties/{id}/features")
    public ResponseEntity<PropertyFeatureListResponse> createFeaturesToProperty(
            @PathVariable("id") Long propertyId,
            @RequestBody List<Feature> propertyFeatureResourceList
    ){
        try{
            Optional<Property> property = propertyService.findById(propertyId);
            if(property.isEmpty()){
                return new ResponseEntity<>(
                        new PropertyFeatureListResponse("Property not found."),
                        HttpStatus.NOT_FOUND
                );
            }
            List<PropertyFeature> propertyFeatureList = new ArrayList<>();
            propertyFeatureResourceList.forEach((feature) ->{
                try {
                    PropertyFeature propertyFeature = new PropertyFeature();
                    propertyFeature.setFeature(feature);
                    propertyFeature.setProperty(property.get());
                    propertyFeatureList.add(propertyFeatureService.save(propertyFeature));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });

            return new ResponseEntity<>(
                    new PropertyFeatureListResponse(propertyFeatureList),
                    HttpStatus.OK
            );
        }catch (Exception e){
            return new ResponseEntity<>(
                    new PropertyFeatureListResponse(e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }
}
