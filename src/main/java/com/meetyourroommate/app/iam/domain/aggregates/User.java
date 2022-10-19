package com.meetyourroommate.app.iam.domain.aggregates;

import java.util.Set;

import javax.persistence.JoinTable;
import javax.persistence.Embedded;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;

import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateRoot;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.meetyourroommate.app.iam.domain.entities.Permission;
import com.meetyourroommate.app.iam.domain.entities.Role;
import com.meetyourroommate.app.iam.domain.valueobjects.Email;
import com.meetyourroommate.app.iam.domain.valueobjects.Password;

import lombok.Data;

@AggregateRoot
@Data
public class User {
  @AggregateIdentifier
  private Long id;

  @Embedded
  private Email email;
  @Embedded
  private Password password;

  private Boolean active;

  @ManyToMany
  @JoinTable(
    name = "user_role",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "role_id"))
  @JsonIgnore
  private Set<Role> roles;

  @ManyToMany
  @JoinTable(
    name = "user_permission",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "permission_id"))
  @JsonIgnore
  private Set<Permission> permissions;
}
