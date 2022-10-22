package com.meetyourroommate.app.iam.interfaces.rest;

import com.meetyourroommate.app.iam.application.services.RoleService;
import com.meetyourroommate.app.iam.domain.entities.Role;
import com.meetyourroommate.app.iam.domain.entities.enums.Roles;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class RoleController {
    private RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }


    @Operation(summary = "Create lessor role", description = "Only create lessor role")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Created new user", content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/roles/lessors")
    public ResponseEntity<?> createLessorRole(){
        try{
            Role lessorRole = new Role();
            lessorRole.setName(Roles.ROLE_USER_LESSOR);
            return new ResponseEntity<>(roleService.save(lessorRole),HttpStatus.OK);
        }catch(Exception e){
           return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Create lessor role", description = "Only create lessor role")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Created new user", content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/roles/students")
    public ResponseEntity<?> createStudentsRole(){
        try{
            Role studentRole = new Role();
            return new ResponseEntity<>(roleService.save(studentRole),HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
