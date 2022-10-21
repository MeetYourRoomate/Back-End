package com.meetyourroommate.app.profile.domain.aggregates;

import com.meetyourroommate.app.iam.domain.aggregates.User;
import com.meetyourroommate.app.profile.domain.valueobjects.Phone;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateRoot;

import javax.persistence.*;
import java.util.Date;

@AggregateRoot
@Entity
public class Profile {
    @Id
    @AggregateIdentifier
    private Long id;
    private String name;
    private String surname;
    @Embedded
    private Phone phone;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Phone getPhone() {
        return phone;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
