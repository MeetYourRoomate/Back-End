package com.meetyourroommate.app.property.domain.entities;

import com.meetyourroommate.app.property.domain.valueobjects.PropertyCategoryId;
import com.meetyourroommate.app.shared.domain.valueobjects.Audit;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PropertyCategory {
    @Id
    private PropertyCategoryId propertyCategoryId;
    private String description;
    @Embedded
    private Audit audit;
}
