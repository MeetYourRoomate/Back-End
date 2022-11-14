package com.meetyourroommate.app.iam.interfaces.rest;

import com.meetyourroommate.app.iam.application.communication.responses.RoleListResponse;
import com.meetyourroommate.app.iam.application.communication.responses.RoleResponse;
import com.meetyourroommate.app.iam.application.services.RoleService;
import com.meetyourroommate.app.iam.domain.entities.Role;
import com.meetyourroommate.app.iam.domain.entities.enums.Roles;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.management.relation.RoleList;
import java.util.List;

@Tag(name = "Role", description = "Create roles")
@RestController
@RequestMapping("/api/v1")
public class RoleController {
    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }


    @Operation(summary = "Create lessor role", description = "Only create lessor role")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Created new user")
    })
    @PostMapping("/roles/lessors")
    public ResponseEntity<RoleResponse> createLessorRole(){
        try{
            Role lessorRole = new Role();
            lessorRole.setName(Roles.ROLE_USER_LESSOR);
            return new ResponseEntity<>(new RoleResponse(roleService.save(lessorRole)),HttpStatus.OK);
        }catch(Exception e){
           return new ResponseEntity<>(new RoleResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Create lessor role", description = "Only create lessor role")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Created new user")
    })
    @PostMapping("/roles/students")
    public ResponseEntity<RoleResponse> createStudentsRole(){
        try{
            Role studentRole = new Role();
            return new ResponseEntity<>(new RoleResponse(roleService.save(studentRole)),HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(new RoleResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Get all roles in db", description = "Get all roles created in db")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Roles")
    })
    @GetMapping(value = "/roles", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RoleListResponse> getAllRoles(){
        try{
            List<Role> roleList = roleService.findAll();
            return new ResponseEntity<>(new RoleListResponse(roleList), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(new RoleListResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
