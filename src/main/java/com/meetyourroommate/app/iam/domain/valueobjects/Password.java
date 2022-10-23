package com.meetyourroommate.app.iam.domain.valueobjects;

import javax.persistence.Embeddable;

import lombok.*;

@Embeddable
public class Password {
  private String value;

  public String getPassword() {
    return value;
  }

  public Password setPassword(String password) {
    this.value = password;
    return this;
  }
}
