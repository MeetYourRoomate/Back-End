package com.meetyourroommate.app.property.domain.aggregates;

import com.meetyourroommate.app.profile.domain.aggregates.Profile;
import com.meetyourroommate.app.property.domain.entities.PropertyFeature;
import com.meetyourroommate.app.property.domain.entities.PropertyAsset;
import com.meetyourroommate.app.shared.domain.valueobjects.Audit;

import javax.persistence.*;
import java.util.List;

@Entity
public class Property {
    @Id
    @GeneratedValue
    private Long id;
    @Lob
    private String title;
    @Lob
    private String description;
    @Column(name = "property_type")
    private String propertyType;
    @Lob
    private String location;
    @ManyToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;
    @OneToMany(mappedBy = "propertyid", cascade = CascadeType.PERSIST)
    private List<PropertyAsset> assets;

    @OneToMany(mappedBy = "property")
    private List<PropertyFeature> propertyFeatureList;
    @Embedded
    private Audit audit = new Audit();
    public Property() {}
    public Property setProfile(Profile profile) {
        this.profile = profile;
        return this;
    }
    public Profile getProfile(){
       return this.profile;
    }
    public List<PropertyAsset> getAssets(){
       return this.assets;
    }
    public String getDescription() {
        return description;
    }

    public Property setDescription(String description) {
        this.description = description;
        return this;
    }
    public Property setPropertyAssets(List<PropertyAsset> newAssets){
       this.assets = newAssets;
       return this;
    }
    public Long getId(){
        return this.id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<PropertyFeature> getPropertyFeatureList() {
        return propertyFeatureList;
    }

    public void setPropertyFeatureList(List<PropertyFeature> propertyFeatureList) {
        this.propertyFeatureList = propertyFeatureList;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }
}
