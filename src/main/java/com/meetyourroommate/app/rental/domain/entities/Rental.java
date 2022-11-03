package com.meetyourroommate.app.rental.domain.entities;

import com.meetyourroommate.app.profile.domain.aggregates.Profile;
import com.meetyourroommate.app.shared.domain.valueobjects.Audit;

import javax.persistence.*;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Entity
public class Rental {
    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    @JoinColumn(name = "agreement_id", unique = true)
    private Agreement agreement;

    @OneToOne
    @JoinColumn(name = "rentaloffert_id", unique = true)
    private RentalOffering rentalOffering;

    @ManyToOne
    @JoinColumn(name = "lessorProfile")
    private Profile lessorProfile;

    @ManyToOne
    @JoinColumn(name = "studenProfile")
    private Profile studentProfile;

    @Embedded
    private Audit audit = new Audit();
    public Rental(){
    }
    public Long getId() {
        return id;
    }
}