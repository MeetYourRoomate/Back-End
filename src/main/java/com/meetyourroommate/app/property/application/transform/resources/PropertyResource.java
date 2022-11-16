package com.meetyourroommate.app.property.application.transform.resources;

import lombok.Data;

import java.io.Serializable;

@Data
public class PropertyResource implements Serializable {
    private String title;
    private String description;
    private String location;
    private String propertyType;
}
