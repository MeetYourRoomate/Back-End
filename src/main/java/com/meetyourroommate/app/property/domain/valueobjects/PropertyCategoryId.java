package com.meetyourroommate.app.property.domain.valueobjects;

import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import java.io.Serializable;

@Embeddable
public class PropertyCategoryId implements Serializable {
    @GeneratedValue
    private Long id;
}
