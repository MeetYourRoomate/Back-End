package com.meetyourroommate.app.propertymanagement.domain.resources;

import com.meetyourroommate.app.propertymanagement.domain.valueobjects.PropertyAsset;
import lombok.Data;

@Data
public class PropertyResource {
    private String description;
    private PropertyAsset propertyAsset;
}
