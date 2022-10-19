package com.meetyourroommate.app.propertymanagement.domain.valueobjects;

import com.meetyourroommate.app.propertymanagement.domain.entities.PropertyFeature;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class Feature implements Serializable {
    private String name;
    private String type;
    private String size;
}
