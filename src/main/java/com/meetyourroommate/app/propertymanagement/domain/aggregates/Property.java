package com.meetyourroommate.app.propertymanagement.domain.aggregates;

import com.meetyourroommate.app.shared.valueobjects.Audit;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateRoot;

import javax.persistence.Embedded;
import javax.persistence.GeneratedValue;

@AggregateRoot
public class Property {
    @AggregateIdentifier
    @GeneratedValue
    private Long id;
    @Embedded
    private Audit audit;
}
