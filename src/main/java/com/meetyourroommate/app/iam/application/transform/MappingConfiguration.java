package com.meetyourroommate.app.iam.application.transform;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("securityMappingConfiguration")
public class MappingConfiguration {

  @Bean
  public UserMapper userMapper() { return new UserMapper(); }

  @Bean
  public RoleMapper roleMapper() { return new RoleMapper(); }

  @Bean
  public PermissionMapper permissionMapper() { return new PermissionMapper(); }

}
