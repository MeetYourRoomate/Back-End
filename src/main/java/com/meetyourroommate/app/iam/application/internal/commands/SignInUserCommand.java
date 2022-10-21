package com.meetyourroommate.app.iam.application.internal.commands;

import com.meetyourroommate.app.iam.domain.valueobjects.Email;
import com.meetyourroommate.app.iam.domain.valueobjects.Password;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import com.meetyourroommate.app.iam.application.communication.AuthenticationRequest;

import lombok.Getter;


@Getter
public class SignInUserCommand {
  @TargetAggregateIdentifier
  private final String id;
  private final Email email;
  private final Password password;

  public SignInUserCommand(AuthenticationRequest request, String id) {
    this.id = id;
    this.email = request.getEmail();
    this.password = request.getPassword();
  }
}
