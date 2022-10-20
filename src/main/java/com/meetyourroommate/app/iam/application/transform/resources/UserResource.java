package com.meetyourroommate.app.iam.application.transform.resources;

import java.util.List;

import com.meetyourroommate.app.iam.domain.valueobjects.Email;

import lombok.Data;

@Data
public class UserResource {
  private Long id;
  private String username;
  private Email email;
  private Boolean active;
  private List<RoleResource> roles;
  private List<PermissionResource> permissions;
}