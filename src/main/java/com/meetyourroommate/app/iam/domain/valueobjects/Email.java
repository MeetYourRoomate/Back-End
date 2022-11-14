package com.meetyourroommate.app.iam.domain.valueobjects;

import javax.persistence.Embeddable;

@Embeddable
public class Email {
  private String address;

  public String getAddress() {
    return address;
  }

  public Email setAddress(String address) {
    this.address = address;
    return this;
  }
}
