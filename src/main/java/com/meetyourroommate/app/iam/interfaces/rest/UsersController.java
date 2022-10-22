package com.meetyourroommate.app.iam.interfaces.rest;

import com.meetyourroommate.app.iam.application.services.RoleService;
import com.meetyourroommate.app.iam.application.services.UserService;
import com.meetyourroommate.app.iam.application.transform.UserMapper;
import com.meetyourroommate.app.iam.application.transform.resources.UserResource;
import com.meetyourroommate.app.iam.domain.aggregates.User;
import com.meetyourroommate.app.iam.domain.entities.Role;
import com.meetyourroommate.app.iam.domain.entities.enums.Roles;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.Optional;

@Tag(name = "Users", description = "Create, read, update and delete users")
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/users")
public class UsersController {

  private UserService userService;

  private RoleService roleService;

  private UserMapper mapper;

  public UsersController(UserService userService, RoleService roleService, UserMapper mapper) {
    this.userService = userService;
    this.roleService = roleService;
    this.mapper = mapper;
  }

  @Operation(summary = "Create user", description = "Create new user")
  @ApiResponses( value = {
          @ApiResponse(responseCode = "200", description = "Created new user", content = @Content(mediaType = "application/json"))
  })
  @PostMapping("/register")
  public ResponseEntity<?> registerUser(@RequestBody UserResource userResource){
    try{
      User newUser = mapper.toEntity(userResource);
      newUser.setId(userResource.getId());
      Optional<Role> studentRole = roleService.findByName(Roles.ROLE_USER_STUDENT);
      if(studentRole.isEmpty()){
        return new ResponseEntity<>("Role not found.", HttpStatus.NOT_FOUND);
      }
      newUser.setRole(studentRole.get());
      return new ResponseEntity<>(newUser,HttpStatus.OK);
    }catch(Exception e){
      return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}