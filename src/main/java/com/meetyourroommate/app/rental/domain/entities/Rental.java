package com.meetyourroommate.app.rental.domain.entities;

import com.meetyourroommate.app.profile.domain.aggregates.Profile;
import com.meetyourroommate.app.rental.application.internal.commands.AcceptRequestCommand;
import com.meetyourroommate.app.rental.application.internal.events.AcceptRequestEvent;
import com.meetyourroommate.app.rental.domain.entities.Agreement;
import com.meetyourroommate.app.rental.domain.entities.RentalOffering;
import com.meetyourroommate.app.rental.domain.entities.RentalRequest;
import com.meetyourroommate.app.rental.domain.enumerate.RentalStatus;
import com.meetyourroommate.app.rental.domain.valueobjects.AgreementId;
import com.meetyourroommate.app.rental.domain.valueobjects.RentalOfferingId;
import com.meetyourroommate.app.shared.domain.valueobjects.Audit;
import lombok.Data;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateRoot;

import javax.persistence.*;
import java.util.List;

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