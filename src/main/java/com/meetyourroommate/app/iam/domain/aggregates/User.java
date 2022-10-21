package com.meetyourroommate.app.iam.domain.aggregates;

import javax.persistence.*;

import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateRoot;
import static org.axonframework.modelling.command.AggregateLifecycle.apply;

import com.meetyourroommate.app.iam.domain.valueobjects.Email;

@AggregateRoot
@Entity
public class User {
  @Id
  @AggregateIdentifier
  private String id;
  @Embedded
  private Email email;
  private Boolean active;
  public User(){
    this.active = true;
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
