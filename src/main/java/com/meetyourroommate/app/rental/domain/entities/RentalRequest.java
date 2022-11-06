package com.meetyourroommate.app.rental.domain.entities;

import com.meetyourroommate.app.profile.domain.aggregates.Profile;
import com.meetyourroommate.app.shared.domain.enumerate.Status;
import com.meetyourroommate.app.shared.domain.valueobjects.Audit;

import javax.persistence.*;


@Entity
public class RentalRequest {
    @Id
    @GeneratedValue
    private Long id;
    @Embedded
    private Audit audit = new Audit();
    @Enumerated(EnumType.STRING)
    private Status status = Status.PENDING;
    private String message;

    @ManyToOne
    @JoinColumn(name = "student_profile_id")
    private Profile studentProfile;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "rental_offert_id")
    private RentalOffering rentalOffering;

    public RentalRequest(){
    }
    public RentalRequest(Profile profile, RentalOffering rentalOffering, String message){
        this.studentProfile = profile;
        this.rentalOffering = rentalOffering;
        this.message = message;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public RentalOffering getRentalOffering() {
        return rentalOffering;
    }

    public void setRentalOffering(RentalOffering rentalOffering) {
        this.rentalOffering = rentalOffering;
    }

    public Profile getStudentProfile() {
        return studentProfile;
    }

    public void setStudentProfile(Profile studentProfile) {
        this.studentProfile = studentProfile;
    }
}
