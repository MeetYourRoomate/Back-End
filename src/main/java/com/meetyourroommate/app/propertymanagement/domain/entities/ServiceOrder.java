package com.meetyourroommate.app.propertymanagement.domain.entities;

import com.meetyourroommate.app.propertymanagement.domain.valueobjects.ServiceOrderId;
import com.meetyourroommate.app.shared.domain.valueobjects.Audit;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ServiceOrder {
    @Id
    private ServiceOrderId serviceOrderId;
    private String state;
    @Embedded
    private Audit audit;
}