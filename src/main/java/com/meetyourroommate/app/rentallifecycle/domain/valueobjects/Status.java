package com.meetyourroommate.app.rentallifecycle.domain.valueobjects;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Date;

@Embeddable
public class Status implements Serializable {
    private String status;
    private Date createdAt;
    private Date updatedAt;
}
