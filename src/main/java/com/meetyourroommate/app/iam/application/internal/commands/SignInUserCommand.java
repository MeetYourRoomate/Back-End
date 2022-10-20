package com.meetyourroommate.app.iam.application.internal.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import com.meetyourroommate.app.iam.application.communication.AuthenticationRequest;

import lombok.Getter;


@Getter
public class SignInUserCommand {
  @TargetAggregateIdentifier
  private final String id;
  private final String email;
  private final String password;

  public SignInUserCommand(AuthenticationRequest request, String id) {
    this.id = id;
    this.email = request.getEmail();
    this.password = request.getPassword();
  }
}
