package com.meetyourroommate.app.rental.application.transform.resources;

import com.meetyourroommate.app.rental.domain.valueobjects.Amount;
import com.meetyourroommate.app.rental.domain.valueobjects.Lifecycle;

public class RentalOfferingResource {
    private Lifecycle lifecycle;
    private Amount amount;
    private String conditions;

    public RentalOfferingResource(){

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

    public String getConditions() {
        return conditions;
    }

    public void setConditions(String conditions) {
        this.conditions = conditions;
    }
}
