package com.meetyourroommate.app.iam.application.communication;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationRequest {
  @NotNull
  @NotBlank
  private String email;
  @NotNull
  @NotBlank
  private String password;
}
