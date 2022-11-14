package com.meetyourroommate.app.iam.application.communication;

import com.meetyourroommate.app.iam.domain.valueobjects.Email;
import com.meetyourroommate.app.iam.domain.valueobjects.Password;
import lombok.Data;

@Data
public class AuthenticationRequest {
  private Email email;
  private Password password;
  public AuthenticationRequest (){}
  public AuthenticationRequest (String email, String password){
    this.email = new Email().setAddress(email);
    this.password = new Password().setPassword(password);
  }

}
