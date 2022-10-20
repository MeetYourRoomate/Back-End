package com.meetyourroommate.app.propertymanagement.domain.entities;

import com.meetyourroommate.app.propertymanagement.domain.aggregates.Property;
import com.meetyourroommate.app.propertymanagement.domain.valueobjects.Feature;
import com.meetyourroommate.app.propertymanagement.domain.valueobjects.PropertyFeatureId;
import com.meetyourroommate.app.shared.domain.valueobjects.Audit;

import javax.persistence.*;

@Entity
public class PropertyFeature {
    @Id
    private PropertyFeatureId propertyFeatureId;

    @ManyToOne
    @JoinColumn(name = "property_feature")
    private Property property;

    @Embedded
    private Feature feature;
    @Embedded
    private Audit audit;
}
