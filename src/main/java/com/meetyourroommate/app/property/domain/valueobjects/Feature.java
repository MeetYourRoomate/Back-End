package com.meetyourroommate.app.property.domain.valueobjects;

import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
public class Feature implements Serializable {
    private String name;
    private String type;
}
