package com.meetyourroommate.app.iam.domain.valueobjects;

import javax.persistence.Embeddable;

import lombok.*;

@Embeddable
public class Email {
  private String adress;

  public String getAdress() {
    return adress;
  }

  public Email setAdress(String adress) {
    this.adress = adress;
    return this;
  }
}
