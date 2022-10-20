package com.meetyourroommate.app.propertymanagement.domain.entities;

import com.meetyourroommate.app.propertymanagement.domain.aggregates.Property;

import javax.persistence.*;

@Entity
public class PropertyAsset {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    @JoinColumn(name = "property_id", nullable = false )
    private Property propertyid;

    @Column(name = "url_image")
    private String urlImage;
}
