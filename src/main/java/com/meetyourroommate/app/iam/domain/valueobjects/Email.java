package com.meetyourroommate.app.iam.domain.valueobjects;

import javax.persistence.Embeddable;

import lombok.Data;

@Embeddable
@Data
public class Email {
  private String value;

  public Boolean isValid(){
    // TODO: Regex to determine if email is valid
    return true;
  }
}
