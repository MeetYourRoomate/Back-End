package com.meetyourroommate.app.iam.domain.entities.enums;

public enum Roles {
  ROLE_USER_STUDENT("ROLE_USER_STUDENT"),
  ROLE_USER_LESSOR("ROLE_USER_LESSOR");
  private final String name;
  Roles(String name){
    this.name = name;
  }
  public String getName(){
    return this.name;
  }
}