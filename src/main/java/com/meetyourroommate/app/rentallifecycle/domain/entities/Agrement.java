package com.meetyourroommate.app.rentallifecycle.domain.entities;

import com.meetyourroommate.app.rentallifecycle.domain.valueobjects.AgrementAsset;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Agrement {
    @Id
    @GeneratedValue
    private Long id;
    @Embedded
    private AgrementAsset agrementAsset;
    @Column(name="summary_of_terms_and_conditions", updatable = false)
    private String summaryOfTermsAndConditions;
    @Column(name="created_at", updatable = false)
    private Date createdAt;
    @Column(name="signed_at", updatable = false)
    private Date signedAt;
    @Column(name="end_at", updatable = false)
    private Date endAt;
}
