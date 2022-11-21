package com.meetyourroommate.app.profile.domain.aggregates;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.meetyourroommate.app.iam.domain.aggregates.User;
import com.meetyourroommate.app.profile.domain.enumerate.TeamStatus;
import com.meetyourroommate.app.profile.domain.entities.Attribute;
import com.meetyourroommate.app.profile.domain.valueobjects.Phone;
import com.meetyourroommate.app.property.domain.aggregates.Property;
import com.meetyourroommate.app.rental.domain.entities.RentalRequest;
import com.meetyourroommate.app.roommate.domain.entities.RoommateRequest;
import com.meetyourroommate.app.shared.domain.valueobjects.Audit;

import javax.persistence.*;
import java.util.*;

@Entity
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long age;
    private String name;
    private String surname;
    @Lob
    @Column(name = "photo_url")
    private String photoUrl;
    private String gender;
    @Lob
    private String about;
    @Lob
    private String country;
    @Lob
    private String city;

    @Enumerated(EnumType.STRING)
    private TeamStatus teamStatus = TeamStatus.WITHOUTTEAM;
    @Embedded
    private Phone phone;
    @Embedded
    @JsonIgnore
    private Audit audit;
    @ManyToMany(fetch = FetchType.LAZY, cascade = {
          CascadeType.PERSIST,
          CascadeType.MERGE
    })
    @JoinTable(name = "profile_atributes",
      joinColumns = { @JoinColumn(name = "profile_id") },
      inverseJoinColumns = { @JoinColumn(name = "atributes_id") })
    private Set<Attribute> attributes = new HashSet<>();

    public void addAtribute(Attribute attribute){
       this.attributes.add(attribute);
       attribute.getProfiles().add(this);
    }
    public void removeAtribute(Attribute attribute){
        this.attributes.remove(attribute);
        attribute.getProfiles().remove(this);
    }

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

    public TeamStatus getTeamStatus() {
        return teamStatus;
    }

    public Profile setTeamStatus(TeamStatus teamStatus) {
        this.teamStatus = teamStatus;
        return this;
    }

    public Long getId() {
        return id;
    }

    public Profile setId(Long id) {
        this.id = id;
        return this;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    public Audit getAudit() {
        return audit;
    }
    public Profile updateAudit(){
        this.audit.setUpdatedAt(new Date());
        return this;
    }

    public Set<Attribute> getAtributesSet() {
        return this.attributes;
    }

    public void setAtributesList(Set<Attribute> attributeList) {
        this.attributes = attributeList;
    }
}
