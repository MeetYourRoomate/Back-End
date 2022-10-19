package com.meetyourroommate.app.iam.domain.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.meetyourroommate.app.iam.domain.aggregates.User;

import lombok.Data;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class Role {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  @NotBlank
  private String name;

  @ManyToMany(mappedBy = "roles")
  @JsonIgnore
  private Set<User> users;
}
