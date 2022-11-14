package com.meetyourroommate.app.rental.domain.entities;

import com.meetyourroommate.app.property.domain.aggregates.Property;
import com.meetyourroommate.app.rental.domain.enumerate.RentalStatus;
import com.meetyourroommate.app.rental.domain.enumerate.Visibility;
import com.meetyourroommate.app.rental.domain.valueobjects.Amount;
import com.meetyourroommate.app.rental.domain.valueobjects.Lifecycle;
import com.meetyourroommate.app.shared.domain.valueobjects.Audit;

import javax.persistence.*;
import java.util.List;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Entity
public class RentalOffering {
    @Id
    @GeneratedValue
    private Long id;
    @Embedded
    private Lifecycle lifecycle;
    @Embedded
    private Amount amount;

    private String conditions;
    @OneToOne
    @JoinColumn(name = "property_id",unique = true)
    private Property property;

    @Enumerated(EnumType.STRING)
    private RentalStatus status = RentalStatus.FREE;

    @Enumerated(EnumType.STRING)
    private Visibility visibility;

    @OneToMany(mappedBy = "rentalOffering", cascade = CascadeType.REMOVE)
    private List<RentalRequest> rentalRequestList;

    @Embedded
    private Audit audit;

    public RentalOffering(Lifecycle lifecycle, Amount amount, String conditions) {
        this.lifecycle = lifecycle;
        this.amount = amount;
        this.conditions = conditions;
        this.audit = new Audit();
    }

    public RentalOffering() {
    }
    public Property getProperty() {
        return property;
    }

    public Lifecycle getLifecycle() {
        return lifecycle;
    }

    public void setLifecycle(Lifecycle lifecycle) {
        this.lifecycle = lifecycle;
    }

    public Amount getAmount() {
        return amount;
    }

    public void setAmount(Amount amount) {
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public String getConditions() {
        return conditions;
    }

    public void setConditions(String conditions) {
        this.conditions = conditions;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public RentalStatus getStatus() {
        return status;
    }

    public RentalOffering setStatus(RentalStatus status) {
        this.status = status;
        return this;
    }

    public Visibility getVisibility() {
        return visibility;
    }

    public void setVisibility(Visibility visibility) {
        this.visibility = visibility;
    }
}
