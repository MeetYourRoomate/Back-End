package com.meetyourroommate.app.propertymanagement.domain.resources;

import com.meetyourroommate.app.propertymanagement.domain.entities.PropertyAsset;
import lombok.Data;

import java.io.Serializable;

@Data
public class PropertyResource implements Serializable {
    private String description;
}
