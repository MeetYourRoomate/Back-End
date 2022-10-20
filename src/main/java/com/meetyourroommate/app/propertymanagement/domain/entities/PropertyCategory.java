package com.meetyourroommate.app.propertymanagement.domain.entities;

import com.meetyourroommate.app.propertymanagement.domain.valueobjects.PropertyCategoryId;
import com.meetyourroommate.app.shared.domain.valueobjects.Audit;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class PropertyCategory {
    @Id
    private PropertyCategoryId propertyCategoryId;

    @Embedded
    private Audit audit;
}
