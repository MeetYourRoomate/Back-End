package com.meetyourroommate.app.iam.domain.valueobjects;

import javax.persistence.Embeddable;

import lombok.*;

@Embeddable
public class Password {
  private String password;

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
