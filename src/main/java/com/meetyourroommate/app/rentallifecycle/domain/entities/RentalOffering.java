package com.meetyourroommate.app.rentallifecycle.domain.entities;

import com.meetyourroommate.app.propertymanagement.domain.valueobjects.PropertyId;
import com.meetyourroommate.app.rentallifecycle.domain.valueobjects.Amount;
import com.meetyourroommate.app.rentallifecycle.domain.valueobjects.Lifecycle;
import com.meetyourroommate.app.shared.valueobjects.Audit;
import lombok.Data;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class RentalOffering {
    @Id
    private Long id;
    @Embedded
    private Lifecycle lifecycle;
    @Embedded
    private Amount amount;
    @Embedded
    private PropertyId propertyId;
    @Embedded
    private Audit audit;
}
