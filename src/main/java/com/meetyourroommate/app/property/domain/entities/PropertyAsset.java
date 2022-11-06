package com.meetyourroommate.app.property.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.meetyourroommate.app.property.domain.aggregates.Property;

import javax.persistence.*;

@Entity
public class PropertyAsset {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    @JoinColumn(name = "property_id", nullable = false )
    @JsonIgnore
    private Property propertyid;

    @Column(name = "url_image")
    private String urlImage;

    public PropertyAsset(){
    }

    public PropertyAsset(String urlImage, Property property){
        this.urlImage = urlImage;
        this.propertyid = property;
    }

    public PropertyAsset setPropertyid(Property propertyid) {
        this.propertyid = propertyid;
        return this;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public Long getId() {
        return id;
    }

    public Property getPropertyid() {
        return propertyid;
    }
}
