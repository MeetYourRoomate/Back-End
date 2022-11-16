package com.meetyourroommate.app.property.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.meetyourroommate.app.property.domain.aggregates.Property;
import com.meetyourroommate.app.property.domain.valueobjects.Feature;
import com.meetyourroommate.app.property.domain.valueobjects.PropertyFeatureId;
import com.meetyourroommate.app.shared.domain.valueobjects.Audit;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class PropertyFeature {
    @Id
    private String id = UUID.randomUUID().toString();
    @ManyToOne
    @JoinColumn(name = "property_feature")
    @JsonIgnore
    private Property property;
    @Embedded
    private Feature feature;
    @Embedded
    private Audit audit = new Audit();

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public Feature getFeature() {
        return feature;
    }

    public void setFeature(Feature feature) {
        this.feature = feature;
    }

    public Audit getAudit() {
        return audit;
    }

    public void setAudit(Audit audit) {
        this.audit = audit;
    }
}
