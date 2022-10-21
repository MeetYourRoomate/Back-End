package com.meetyourroommate.app.rental.domain.aggregates;

import com.meetyourroommate.app.rental.domain.entities.RentalRequest;
import com.meetyourroommate.app.rental.domain.valueobjects.AgreementId;
import com.meetyourroommate.app.rental.domain.valueobjects.RentalOfferingId;
import com.meetyourroommate.app.shared.domain.valueobjects.Audit;
import lombok.Data;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateRoot;

import javax.persistence.Column;
import javax.persistence.Embedded;
import java.util.List;

@AggregateRoot
@Data
public class Rental {
    @AggregateIdentifier
    private Long id;
    @Embedded
    @Column(name = "agreement_id")
    private AgreementId agreementId;

    @Embedded
    @Column(name = "rental_offering_id")
    private RentalOfferingId rentalOfferingId;

    private List<RentalRequest> rentalRequestList;
    @Embedded
    private Audit audit;
}