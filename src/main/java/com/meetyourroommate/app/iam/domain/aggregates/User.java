package com.meetyourroommate.app.iam.domain.aggregates;

import java.util.Optional;
import java.util.Set;

import javax.persistence.*;

import com.meetyourroommate.app.iam.application.services.UserService;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateRoot;
import static org.axonframework.modelling.command.AggregateLifecycle.apply;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.meetyourroommate.app.iam.application.internal.commands.SignInUserCommand;
import com.meetyourroommate.app.iam.application.internal.events.UserSignedInEvent;
import com.meetyourroommate.app.iam.domain.entities.Permission;
import com.meetyourroommate.app.iam.domain.entities.Role;
import com.meetyourroommate.app.iam.domain.valueobjects.Email;
import com.meetyourroommate.app.iam.domain.valueobjects.Password;
import com.meetyourroommate.app.iam.infrastructure.persistance.jpa.UserRepository;
import com.meetyourroommate.app.shared.application.exceptions.ResourceValidationException;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.With;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Transient;

@AggregateRoot
@Entity
public class User {
  @Id
  @AggregateIdentifier
  @GeneratedValue
  private Long id;

  @Embedded
  private Email email;
  @Embedded
  private Password password;

  private Boolean active;

  public Long getId() {
    return id;
  }

  /*  @ManyToMany
  @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
  @JsonIgnore
  private Set<Role> roles;

  @ManyToMany
  @JoinTable(name = "user_permission", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "permission_id"))
  @JsonIgnore
  private Set<Permission> permissions;

  @Autowired
  @JsonIgnore
  private UserService userService;
  @CommandHandler
  public void handle(SignInUserCommand command) {

    Optional<User> userOptional = userRepository.findByEmail(command.getEmail());

    if (!userOptional.isPresent()) {
      throw new ResourceValidationException("User with email: " + command.getEmail() + " not found");
    }

    User user = userOptional.get();
    if (user.getPassword().confirmPassword(command.getPassword())) {
      apply(new UserSignedInEvent(user.getId(), user.getEmail(), user.getPassword()));
    }
  }

  @EventSourcingHandler
  public void on(UserSignedInEvent event) {
    this.id = event.getId();
    this.email = event.getEmail();
    this.password = event.getPassword();
    this.active = true;
  }*/
}
