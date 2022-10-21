package com.meetyourroommate.app.property.domain.valueobjects;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class Feature implements Serializable {
    private String name;
    private String type;
    private String size;
}
