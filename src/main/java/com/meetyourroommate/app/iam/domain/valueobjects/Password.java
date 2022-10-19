package com.meetyourroommate.app.iam.domain.valueobjects;

import javax.persistence.Embeddable;

import lombok.Data;

@Embeddable
@Data
public class Password {
  private String value;
  private String recoveryToken;

  public String HashPassword(){
    // TODO: Hash password
    return this.value;
  }
}
