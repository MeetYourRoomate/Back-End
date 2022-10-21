package com.meetyourroommate.app.property.domain.entities;

import com.meetyourroommate.app.property.domain.aggregates.Property;

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

    public PropertyAsset(){
    }

    public PropertyAsset(String urlImage, Property property){
        this.urlImage = urlImage;
        this.propertyid = property;
    }

    public void setPropertyid(Property propertyid) {
        this.propertyid = propertyid;
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
}
