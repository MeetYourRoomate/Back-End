package com.meetyourroommate.app.profile.interfaces.rest;

import com.meetyourroommate.app.profile.application.communication.responses.AtributeDtoListResponse;
import com.meetyourroommate.app.profile.application.communication.responses.AtributeDtoResponse;
import com.meetyourroommate.app.profile.application.services.AttributeService;
import com.meetyourroommate.app.profile.application.services.ProfileService;
import com.meetyourroommate.app.profile.application.transform.AtributeDtoMapper;
import com.meetyourroommate.app.profile.application.transform.AtributeResourceMapper;
import com.meetyourroommate.app.profile.application.transform.resources.AtributeResource;
import com.meetyourroommate.app.profile.domain.aggregates.Profile;
import com.meetyourroommate.app.profile.domain.entities.Attribute;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1")
public class AttributeController {

    private final AttributeService attributeService;
    private final AtributeDtoMapper atributeDtoMapper;
    private final AtributeResourceMapper atributeResourceMapper;
    private final ProfileService profileService;

    public AttributeController(AttributeService attributeService, AtributeDtoMapper atributeDtoMapper, AtributeResourceMapper atributeResourceMapper, ProfileService profileService) {
        this.attributeService = attributeService;
        this.atributeDtoMapper = atributeDtoMapper;
        this.atributeResourceMapper = atributeResourceMapper;
        this.profileService = profileService;
    }

    @Tag(name = "Attribute", description = "Create, read, update and delete Attribute")
    @Operation(summary = "List all attributes", description = "List all attributes")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Listed all attributes")
    })
    @GetMapping(value = "/attributes", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AtributeDtoListResponse> getAllAtributes(){
        try{
            List<Attribute> attributes = attributeService.findAll();
            return new ResponseEntity<>(
                    new AtributeDtoListResponse(
                            atributeDtoMapper.toDtoList(attributes)
                    ),
                    HttpStatus.OK
            );
        }catch (Exception e){
            return new ResponseEntity<>(
                    new AtributeDtoListResponse(e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @Tag(name = "Attribute", description = "Create, read, update and delete Attribute")
    @Operation(summary = "Create new attribute", description = "Create new attribute ")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Created new attribute")
    })
    @PostMapping(value = "/attributes", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AtributeDtoResponse> createAttribute(@RequestBody AtributeResource model){
        try{
            Attribute attribute = atributeResourceMapper.toEntity(model);
            return new ResponseEntity<>(
                    new AtributeDtoResponse(
                            atributeDtoMapper.toDto(attributeService.save(attribute))
                    ),
                    HttpStatus.OK
            );
        }catch (Exception e){
            return new ResponseEntity<>(
                    new AtributeDtoResponse(e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }
    @Operation(summary = "Assign attribute to profile", description = "Assign attribute to profile by ids", tags = {"Profile"})
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Assigned attribute")
    })
    @PutMapping(value = "/profiles/{profile_id}/attributes{atribute_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AtributeDtoListResponse> assignAttributeToProfile(
            @PathVariable("profile_id") Long profileId,
            @PathVariable("atribute_id") String attributeId
    ){
        try{
            Optional<Profile> profile = profileService.findById(profileId);
            if(profile.isEmpty()){
                return new ResponseEntity<>(
                        new AtributeDtoListResponse("Profile not found"),
                        HttpStatus.NOT_FOUND
                );
            }
            Optional<Attribute> atribute = attributeService.findById(attributeId);
            if(atribute.isEmpty()){
                return new ResponseEntity<>(
                        new AtributeDtoListResponse("Atribute not found"),
                        HttpStatus.NOT_FOUND
                );
            }
            profile.get().addAtribute(atribute.get());
            profileService.save(profile.get());
            return new ResponseEntity<>(
                    new AtributeDtoListResponse(atributeDtoMapper.toDtoList(profile.get().getAtributesSet())),
                    HttpStatus.OK
            );

        }catch (Exception e){
            return new ResponseEntity<>(
                    new AtributeDtoListResponse(e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @Operation(summary = "Get All attribute by profile id", description = "Get all attributes by profile id", tags = {"Profile"})
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Attributes")
    })
    @GetMapping(value = "/profile/{id}/attributes", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AtributeDtoListResponse> getAllAtributesOfProfile(@PathVariable("id") Long profileId){
       try{
           Optional<Profile> profile = profileService.findById(profileId);
           if(profile.isEmpty()){
               return new ResponseEntity<>(
                       new AtributeDtoListResponse("Profile not found."),
                       HttpStatus.NOT_FOUND
               );
           }
           return new ResponseEntity<>(
                   new AtributeDtoListResponse(atributeDtoMapper.toDtoList(profile.get().getAtributesSet())),
                   HttpStatus.OK
           );
       }catch (Exception e){
          return new ResponseEntity<>(
                  new AtributeDtoListResponse(e.getMessage()),
                  HttpStatus.INTERNAL_SERVER_ERROR
          );
       }
    }
}
