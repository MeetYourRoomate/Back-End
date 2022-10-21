package com.meetyourroommate.app.iam.application.transform.resources;

import java.util.List;

import com.meetyourroommate.app.iam.domain.valueobjects.Email;

import com.meetyourroommate.app.iam.domain.valueobjects.Password;
import lombok.Data;

@Data
public class UserResource {
  private String username;
  private Email email;
  private Password password;
}