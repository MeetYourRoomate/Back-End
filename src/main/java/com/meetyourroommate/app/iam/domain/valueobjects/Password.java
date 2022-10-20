package com.meetyourroommate.app.iam.domain.valueobjects;

import javax.persistence.Embeddable;

import lombok.*;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@With
public class Password {
  private String value;
  private String recoveryToken;

  public String HashPassword() {
    // TODO: Hash password
    return this.value;
  }

  public boolean confirmPassword(String password) {
    return this.value.equals(password);
  }
}
