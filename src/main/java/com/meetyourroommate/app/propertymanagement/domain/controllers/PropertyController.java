package com.meetyourroommate.app.propertymanagement.domain.controllers;

import com.meetyourroommate.app.propertymanagement.domain.aggregates.Property;
import com.meetyourroommate.app.propertymanagement.domain.resources.PropertyResource;
import com.meetyourroommate.app.propertymanagement.domain.service.PropertyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/property")
public class PropertyController {

    @Autowired
    private PropertyService propertyService;

	@Operation(summary = "Create new property", description = "Create property to lessor", tags = {"property"})
	@ApiResponses( value = {
		@ApiResponse(responseCode = "200", description = "Created property", content = @Content(mediaType = "application/json"))
	})
	@PostMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public Property save(@RequestBody PropertyResource entity) throws Exception{
        Property newProperty = new Property();
        newProperty.setDescription(entity.getDescription());
        newProperty.setPropertyAsset(entity.getPropertyAsset());
        return propertyService.save(newProperty);
    }
}
