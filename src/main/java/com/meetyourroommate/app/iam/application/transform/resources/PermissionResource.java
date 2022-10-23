package com.meetyourroommate.app.iam.application.transform.resources;

import lombok.Data;

@Data
public class PermissionResource {
  private Long id;
  private String name;
  private String route;
}
