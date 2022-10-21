package com.meetyourroommate.app.iam.application.internal.events;

import com.meetyourroommate.app.iam.domain.valueobjects.Email;
import com.meetyourroommate.app.iam.domain.valueobjects.Password;

import lombok.Getter;

@Getter
public class UserSignedInEvent {
  private Long id;
  private Email email;
  private Password password;

  public UserSignedInEvent(Long id, Email email, Password password) {
    this.id = id;
    this.email = email;
    this.password = password;
  }
}
