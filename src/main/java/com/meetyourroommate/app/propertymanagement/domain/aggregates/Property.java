package com.meetyourroommate.app.propertymanagement.domain.aggregates;

import com.meetyourroommate.app.profile.domain.aggregates.Profile;
import com.meetyourroommate.app.propertymanagement.domain.entities.PropertyFeature;
import com.meetyourroommate.app.propertymanagement.domain.entities.PropertyAsset;
import com.meetyourroommate.app.propertymanagement.domain.valueobjects.PropertyId;
import com.meetyourroommate.app.shared.domain.valueobjects.Audit;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateRoot;

import javax.persistence.*;
import java.util.List;

@AggregateRoot
@Entity
public class Property {

    @Id
    @AggregateIdentifier
    @GeneratedValue
    private Long id;
    private String description;
    @ManyToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;
    @OneToMany(mappedBy = "propertyid")
    List<PropertyAsset> assets;

    @OneToMany(mappedBy = "property")
    List<PropertyFeature> propertyFeatureList;
    @Embedded
    private Audit audit;
    public Property() {
        this.audit = new Audit();
    }
    public Property setProfile(Profile profile) {
        this.profile = profile;
        return this;
    }
    public Profile getProfile(){
       return this.profile;
    }
    public List<PropertyAsset> getPropertyAssets(){
       return this.assets;
    }
    public String getDescription() {
        return description;
    }

    public Property setDescription(String description) {
        this.description = description;
        return this;
    }
    public Long getId(){
        return this.id;
    }
}
