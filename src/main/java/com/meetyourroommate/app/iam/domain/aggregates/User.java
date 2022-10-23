package com.meetyourroommate.app.iam.domain.aggregates;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.meetyourroommate.app.iam.domain.entities.Role;
import com.meetyourroommate.app.profile.domain.aggregates.Profile;
import com.meetyourroommate.app.shared.domain.valueobjects.Audit;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateRoot;

import com.meetyourroommate.app.iam.domain.valueobjects.Email;

@AggregateRoot
@Entity
@Table(name = "users")
public class User {
  @Id
  @AggregateIdentifier
  private String id;
  @Embedded
  private Email email;
  private Boolean active;

  @Embedded
  @JsonIgnore
  private Audit audit;

  @OneToOne(mappedBy = "user", cascade = CascadeType.REMOVE)
  @JsonIgnore
  private Profile profile;

  @ManyToOne()
  @JoinColumn(name = "role_id")
  private Role role;

  public User(){
    this.active = true;
    this.audit = new Audit();
  }

  public String getId() {
    return this.id;
  }
  public User setId(String id) {
    this.id = id;
    return this;
  }

  public Email getEmail() {
    return email;
  }

  public void setEmail(Email email) {
    this.email = email;
  }

  public Boolean getActive() {
    return active;
  }

  public void setActive(Boolean active) {
    this.active = active;
  }

  public Audit getAudit() {
    return audit;
  }

  public void setAudit(Audit audit) {
    this.audit = audit;
  }

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }
}
