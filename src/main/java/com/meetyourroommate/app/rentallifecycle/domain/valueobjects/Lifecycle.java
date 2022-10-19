package com.meetyourroommate.app.rentallifecycle.domain.valueobjects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Date;

@Embeddable
public class Lifecycle implements Serializable {
    @Column(name="created_at", updatable = false)
    private Date createdAt;
    @Column(name="end_at", updatable = false)
    private Date endAt;
}
