package com.meetyourroommate.app.profile.domain.aggregates;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.meetyourroommate.app.iam.domain.aggregates.User;
import com.meetyourroommate.app.profile.domain.enumerate.TeamStatus;
import com.meetyourroommate.app.profile.domain.entities.Atribute;
import com.meetyourroommate.app.profile.domain.valueobjects.Phone;
import com.meetyourroommate.app.property.domain.aggregates.Property;
import com.meetyourroommate.app.rental.domain.entities.RentalRequest;
import com.meetyourroommate.app.roommate.domain.entities.RoommateRequest;
import com.meetyourroommate.app.shared.domain.valueobjects.Audit;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

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
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Atribute> atributes;

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

    public List<Atribute> getAtributesList() {
        return this.atributes;
    }

    public void setAtributesList(List<Atribute> atributeList) {
        this.atributes = atributeList;
    }
}
