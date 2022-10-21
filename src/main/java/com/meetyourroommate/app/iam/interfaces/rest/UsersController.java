package com.meetyourroommate.app.iam.interfaces.rest;

import javax.validation.Valid;

import com.meetyourroommate.app.iam.application.services.UserService;
import com.meetyourroommate.app.iam.application.transform.resources.UserResource;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.meetyourroommate.app.iam.application.communication.AuthenticationRequest;
import com.meetyourroommate.app.iam.application.communication.RegistrationRequest;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Users", description = "Create, read, update and delete users")
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/users")
public class UsersController {

  @Autowired
  private UserService userService;

/*  private final CommandGateway gateway;

  // private final UserService userService;
  // private final UserMapper mapper;

  public UsersController(CommandGateway gateway) {
    this.gateway = gateway;
  }

  @PostMapping("/auth/sign-in")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody AuthenticationRequest request) {
    // return userService.authenticate(request);
    return null;
  }

  @PostMapping("/auth/sign-up")
  public ResponseEntity<?> registerUser(@Valid @RequestBody RegistrationRequest request) {
    // return userService.register(request);
    return null;
  }
*/
  @PostMapping("")
  public ResponseEntity<?> registerUser(@RequestBody UserResource userResource){
    try{
      return new ResponseEntity<>(HttpStatus.OK);
    }catch(Exception e){
      return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  // @GetMapping
  // @PreAuthorize("hasRole('ADMIN')")
  // public ResponseEntity<?> getAllUsers(Pageable pageable) {
  //   Page<UserResource> resources = mapper.modelListToPage(userService.getAll(), pageable);
  //   return ResponseEntity.ok(resources);
  // }
}