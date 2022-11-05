package com.meetyourroommate.app.profile.domain.aggregates;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.meetyourroommate.app.iam.domain.aggregates.User;
import com.meetyourroommate.app.profile.domain.valueobjects.Phone;
import com.meetyourroommate.app.property.domain.aggregates.Property;
import com.meetyourroommate.app.rental.domain.entities.RentalRequest;
import com.meetyourroommate.app.roommate.domain.entities.RoommateRequest;
import com.meetyourroommate.app.shared.domain.valueobjects.Audit;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateRoot;

import javax.persistence.*;
import java.util.List;

@Entity
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    @Embedded
    private Phone phone;
    @Embedded
    @JsonIgnore
    private Audit audit;

    @OneToMany(mappedBy = "studentProfile")
    @JsonIgnore
    private List<RentalRequest> rentalRequest;
    public Profile(){
       this.audit = new Audit();
    }

    @OneToMany(mappedBy = "profile", cascade = CascadeType.REMOVE)
    private List<Property> properties;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "user_id", unique = true)
    @JsonIgnore
    private User user;

    @JsonIgnore
    @OneToMany(mappedBy = "studentRequested", cascade = CascadeType.REMOVE)
    private List<RoommateRequest> roommateRequest;

    @JsonIgnore
    @OneToMany(mappedBy = "studentRequestor", cascade = CascadeType.REMOVE)
    private List<RoommateRequest> roommateRequestors;

    public List<RoommateRequest> getRoommateRequest(){
        return this.roommateRequest;
    }
    public List<RoommateRequest> getRoommateRequestors(){
        return this.roommateRequestors;
    }
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Phone getPhone() {
        return phone;
    }
    public Profile setPhone(Phone phone){
       this.phone = phone;
       return this;
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

    public void setAudit(Audit audit) {
        this.audit = audit;
    }
}
