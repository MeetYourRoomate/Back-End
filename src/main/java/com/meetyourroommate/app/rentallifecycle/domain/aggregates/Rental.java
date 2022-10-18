package com.meetyourroommate.app.rentallifecycle.domain.aggregates;

import com.meetyourroommate.app.rentallifecycle.domain.entities.Agreement;
import com.meetyourroommate.app.rentallifecycle.domain.entities.RentalOffering;
import com.meetyourroommate.app.rentallifecycle.domain.entities.RentalRequest;
import com.meetyourroommate.app.rentallifecycle.domain.valueobjects.AgreementId;
import com.meetyourroommate.app.rentallifecycle.domain.valueobjects.RentalOfferingId;
import com.meetyourroommate.app.shared.valueobjects.Audit;
import lombok.Data;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateRoot;

import javax.persistence.Embedded;
import java.util.List;

@AggregateRoot
@Data
public class Rental {
    @AggregateIdentifier
    private Long id;
    @Embedded
    private AgreementId agreementId;
    @Embedded
    private RentalOfferingId rentalOfferingId;

    private List<RentalRequest> rentalRequestList;
    @Embedded
    private Audit audit;
}