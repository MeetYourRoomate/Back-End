package com.meetyourroommate.app.propertymanagement.domain.entities;

import com.meetyourroommate.app.shared.valueobjects.Audit;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class PropertyCategory {
    @Id
    @GeneratedValue
    private Long id;
    @Embedded
    private Audit audit;
}
