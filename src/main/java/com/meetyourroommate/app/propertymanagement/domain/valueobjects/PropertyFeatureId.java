package com.meetyourroommate.app.propertymanagement.domain.valueobjects;

import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import java.io.Serializable;

@Embeddable
public class PropertyFeatureId implements Serializable {
    @GeneratedValue
    private Long id;
}
