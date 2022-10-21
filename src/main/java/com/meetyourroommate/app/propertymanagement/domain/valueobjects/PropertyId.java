package com.meetyourroommate.app.propertymanagement.domain.valueobjects;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import java.io.Serializable;

@Embeddable
@Data
public class PropertyId implements Serializable {

    @GeneratedValue
    @Column(name = "property_id", unique = true, updatable = false)
    private Long id;

    public PropertyId(){
    }
    public PropertyId(Long id){
        this.id = id;
    }
}
