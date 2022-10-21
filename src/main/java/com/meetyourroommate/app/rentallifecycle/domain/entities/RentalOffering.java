package com.meetyourroommate.app.rentallifecycle.domain.entities;

import com.meetyourroommate.app.propertymanagement.domain.aggregates.Property;
import com.meetyourroommate.app.rentallifecycle.domain.valueobjects.Amount;
import com.meetyourroommate.app.rentallifecycle.domain.valueobjects.Lifecycle;
import com.meetyourroommate.app.shared.domain.valueobjects.Audit;

import javax.persistence.*;

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
    @JoinColumn(name = "property_id")
    private Property property;

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

    public Audit getAudit() {
        return audit;
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
}
