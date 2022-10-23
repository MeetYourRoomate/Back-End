package com.meetyourroommate.app.iam.application.communication;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.meetyourroommate.app.iam.domain.valueobjects.Email;
import com.meetyourroommate.app.iam.domain.valueobjects.Password;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class AuthenticationRequest {
  private Email email;
  private Password password;
  public AuthenticationRequest (){}
  public AuthenticationRequest (String email, String password){
    this.email = new Email().setAdress(email);
    this.password = new Password().setPassword(password);
  }

}
