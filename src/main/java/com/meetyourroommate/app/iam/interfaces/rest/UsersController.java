package com.meetyourroommate.app.iam.interfaces.rest;

import com.meetyourroommate.app.iam.application.communication.responses.UserResponse;
import com.meetyourroommate.app.iam.application.services.RoleService;
import com.meetyourroommate.app.iam.application.services.UserService;
import com.meetyourroommate.app.iam.application.transform.UserMapper;
import com.meetyourroommate.app.iam.application.transform.resources.UserResource;
import com.meetyourroommate.app.iam.domain.aggregates.User;
import com.meetyourroommate.app.iam.domain.entities.Role;
import com.meetyourroommate.app.iam.domain.entities.enums.Roles;
import com.meetyourroommate.app.iam.domain.valueobjects.Email;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.Optional;

@Tag(name = "Users", description = "Create, read, update and delete users")
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/users")
public class UsersController {

  private final UserService userService;

  private final RoleService roleService;

  private final UserMapper mapper;

  public UsersController(UserService userService, RoleService roleService, UserMapper mapper) {
    this.userService = userService;
    this.roleService = roleService;
    this.mapper = mapper;
  }

  @Operation(summary = "Create user", description = "Create new user")
  @ApiResponses( value = {
          @ApiResponse(responseCode = "200", description = "Created new user")
  })
  @PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<UserResponse> registerUser(@RequestBody UserResource userResource){
    try{
      Optional<User> userOptional = userService.findById(userResource.getId());
      if(userOptional.isPresent()){
        return new ResponseEntity<>(new UserResponse("User has already been created"), HttpStatus.CONFLICT);
      }
      User newUser = mapper.toEntity(userResource);
      newUser.setId(userResource.getId());
      Optional<Role> studentRole = roleService.findByName(Roles.ROLE_USER_STUDENT);
      if(studentRole.isEmpty()){
        return new ResponseEntity<>(new UserResponse("Role not found."), HttpStatus.NOT_FOUND);
      }
      newUser.setRole(studentRole.get());
      return new ResponseEntity<>(new UserResponse(userService.save(newUser)),HttpStatus.OK);
    }catch(Exception e){
      return new ResponseEntity<>(new UserResponse(e.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @Operation(summary = "Update user role", description = "update existing users with a lessor role")
  @ApiResponses( value = {
          @ApiResponse(responseCode = "200", description = "Updated user with new role")
  })
  @PutMapping(value = "/{id}/assign/lessor", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<UserResponse> assignLessorRoleToUser(@PathVariable String id){
    try{
      Optional<User> user = userService.findById(id);
      if(user.isEmpty()){
        return new ResponseEntity<>( new UserResponse("User not found."), HttpStatus.NOT_FOUND);
      }
      Optional<Role> roleLessor = roleService.findByName(Roles.ROLE_USER_LESSOR);
      if(roleLessor.isEmpty()){
        return new ResponseEntity<>(new UserResponse("Lessor role not found."), HttpStatus.NOT_FOUND);
      }
      user.get().setRole(roleLessor.get());
      return new ResponseEntity<>(new UserResponse(userService.save(user.get())), HttpStatus.OK);
    }catch(Exception e){
      return new ResponseEntity<>(new UserResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @Operation(summary = "Delete user", description = "Deletes user and all associated elements")
  @ApiResponses( value = {
          @ApiResponse(responseCode = "200", description = "Deleted user")
  })
  @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<UserResponse>deleteUser(@PathVariable String id){
    try{
      userService.deleteById(id);
      return new ResponseEntity<>(new UserResponse("User " + id + " has been deleted"),HttpStatus.OK);
    }catch (Exception e){
      return new ResponseEntity<>(new UserResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @Operation(summary = "Get user by id", description = "Get user by id")
  @ApiResponses( value = {
          @ApiResponse(responseCode = "200", description = "User")
  })
  @GetMapping(value = "/{id}")
  public ResponseEntity<UserResponse>getUserById(@PathVariable String id){
    try{
      Optional<User> user = userService.findById(id);
      if(user.isEmpty()){
        return new ResponseEntity<>(new UserResponse("User not found."), HttpStatus.NOT_FOUND);
      }
      return new ResponseEntity<>(new UserResponse(user.get()), HttpStatus.OK);
    }catch(Exception e){
      return new ResponseEntity<>(new UserResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @Operation(summary = "Get user by email", description = "Get user by email")
  @ApiResponses( value = {
          @ApiResponse(responseCode = "200", description = "User")
  })
  @GetMapping(value = "",produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<UserResponse>getUserByEmail(@RequestParam String email){
    try{
      Optional<User> user = userService.findUserByEmail(new Email().setAddress(email));
      if(user.isEmpty()){
        return new ResponseEntity<>(new UserResponse("User not found"), HttpStatus.NOT_FOUND);
      }
      return new ResponseEntity<>(new UserResponse(user.get()), HttpStatus.OK);
    }catch(Exception e){
      return new ResponseEntity<>(new UserResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}