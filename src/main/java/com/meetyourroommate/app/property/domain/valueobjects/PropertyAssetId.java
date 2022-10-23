package com.meetyourroommate.app.property.domain.valueobjects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import java.io.Serializable;

@Embeddable
public class PropertyAssetId implements Serializable {
    @GeneratedValue
    @Column(name = "id")
    private Long id;
}
