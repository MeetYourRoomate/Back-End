package com.meetyourroommate.app.rental.domain.aggregates;

import com.meetyourroommate.app.rental.application.internal.commands.CreateRentalRequestCommand;
import com.meetyourroommate.app.rental.application.internal.events.CreateRentalRequestEvent;
import com.meetyourroommate.app.shared.domain.enumerate.Status;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import javax.persistence.Entity;
import javax.persistence.Id;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
@Slf4j
public class RentalRequestAggregate {
    @AggregateIdentifier
    private String id;

    private Status status = Status.PENDING;
    private String message;
    private String userId;
    private Long offerId;
    public RentalRequestAggregate(){

    }
    @CommandHandler
    public RentalRequestAggregate(CreateRentalRequestCommand createRentalRequestCommand){
        log.info("Create RentalRequest received.");
        CreateRentalRequestEvent createRentalRequestEvent = new CreateRentalRequestEvent(
                createRentalRequestCommand.getRequestId(),
                createRentalRequestCommand.getMessage(),
                createRentalRequestCommand.getUserId(),
                createRentalRequestCommand.getOfferId()
        );
        apply(createRentalRequestEvent);
    }

    @EventSourcingHandler
    public void on(CreateRentalRequestEvent createRentalRequestEvent){
        log.info("An Create RentalRequest Event ocurred.");
        this.id = createRentalRequestEvent.getRequestId();
        this.message = createRentalRequestEvent.getMessage();
        this.offerId = createRentalRequestEvent.getOfferId();
        this.userId = createRentalRequestEvent.getUserId();
    }
}
