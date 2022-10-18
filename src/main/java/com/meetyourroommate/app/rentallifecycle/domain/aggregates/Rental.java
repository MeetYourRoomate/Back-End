package com.meetyourroommate.app.rentallifecycle.domain.aggregates;

import com.meetyourroommate.app.rentallifecycle.domain.entities.Agrement;
import com.meetyourroommate.app.rentallifecycle.domain.entities.RentalOffering;
import com.meetyourroommate.app.rentallifecycle.domain.entities.RentalRequest;
import lombok.Data;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateRoot;

import java.util.List;

@AggregateRoot
@Data
public class Rental {
    @AggregateIdentifier
    private Long id;
    private Agrement agrement;
    private RentalOffering rentalOffering;
    private List<RentalRequest> rentalRequestList;
}