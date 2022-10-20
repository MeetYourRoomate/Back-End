package com.meetyourroommate.app.shared.domain.valueobjects;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Date;

@Embeddable
public class Audit implements Serializable {
    private Date createdAt;
    private Date updatedAt;
}
