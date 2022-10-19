package com.meetyourroommate.app.propertymanagement.domain.valueobjects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PropertyAsset {
    @Column(name = "url_image")
    private String urlImage;
}
