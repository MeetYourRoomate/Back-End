package com.meetyourroommate.app.profile.interfaces.rest;

import com.meetyourroommate.app.profile.application.communication.responses.AtributeDtoListResponse;
import com.meetyourroommate.app.profile.application.communication.responses.AtributeDtoResponse;
import com.meetyourroommate.app.profile.application.services.AtributeService;
import com.meetyourroommate.app.profile.application.services.ProfileService;
import com.meetyourroommate.app.profile.application.transform.AtributeDtoMapper;
import com.meetyourroommate.app.profile.application.transform.AtributeResourceMapper;
import com.meetyourroommate.app.profile.application.transform.resources.AtributeResource;
import com.meetyourroommate.app.profile.domain.aggregates.Profile;
import com.meetyourroommate.app.profile.domain.entities.Atribute;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1")
public class AtributeController {

    private final AtributeService atributeService;
    private final AtributeDtoMapper atributeDtoMapper;
    private final AtributeResourceMapper atributeResourceMapper;
    private final ProfileService profileService;

    public AtributeController(AtributeService atributeService, AtributeDtoMapper atributeDtoMapper, AtributeResourceMapper atributeResourceMapper, ProfileService profileService) {
        this.atributeService = atributeService;
        this.atributeDtoMapper = atributeDtoMapper;
        this.atributeResourceMapper = atributeResourceMapper;
        this.profileService = profileService;
    }

    @Tag(name = "Atribute", description = "Create, read, update and delete Atribute")
    @Operation(summary = "List all atributes", description = "List all atributes")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Liested all atributes")
    })
    @GetMapping(value = "/atributes", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AtributeDtoListResponse> getAllAtributes(){
        try{
            List<Atribute> atributes = atributeService.findAll();
            return new ResponseEntity<>(
                    new AtributeDtoListResponse(
                            atributeDtoMapper.toDtoList(atributes)
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

    @Tag(name = "Atribute", description = "Create, read, update and delete Atribute")
    @Operation(summary = "Create new atribute", description = "Create new atribute ")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Created new atribute")
    })
    @PostMapping(value = "/atributes", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AtributeDtoResponse> createAtribute(@RequestBody AtributeResource model){
        try{
            Atribute atribute = atributeResourceMapper.toEntity(model);
            return new ResponseEntity<>(
                    new AtributeDtoResponse(
                            atributeDtoMapper.toDto(atributeService.save(atribute))
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
    @Operation(summary = "Assign atribute to profile", description = "Assign atribute to profile by ids", tags = {"Profile"})
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Assigned atribute")
    })
    @PutMapping(value = "/profiles/{profile_id}/atributes{atribute_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AtributeDtoListResponse> assignAtributeToProfile(
            @PathVariable("profile_id") Long profileId,
            @PathVariable("atribute_id") String atributeId
    ){
        try{
            Optional<Profile> profile = profileService.findById(profileId);
            if(profile.isEmpty()){
                return new ResponseEntity<>(
                        new AtributeDtoListResponse("Profile not found"),
                        HttpStatus.NOT_FOUND
                );
            }
            Optional<Atribute> atribute = atributeService.findById(atributeId);
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

    @Operation(summary = "Get All atribute by profile id", description = "Get all atributes by profile id", tags = {"Profile"})
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Atributes")
    })
    @GetMapping(value = "/profile/{id}/atributes", produces = MediaType.APPLICATION_JSON_VALUE)
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
                   HttpStatus.INTERNAL_SERVER_ERROR
           );
       }catch (Exception e){
          return new ResponseEntity<>(
                  new AtributeDtoListResponse(e.getMessage()),
                  HttpStatus.INTERNAL_SERVER_ERROR
          );
       }
    }
}
