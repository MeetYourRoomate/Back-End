package com.meetyourroommate.app.iam.application.transform.resources;

import java.util.List;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@With
public class AuthenticationResource {
  private String id;
  private String username;
  private String email;
  private List<String> roles;
  private List<String> permissions;
  private String token;

}
