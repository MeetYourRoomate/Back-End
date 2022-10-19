package com.meetyourroommate.app.rentallifecycle.domain.entities;

import com.meetyourroommate.app.rentallifecycle.domain.valueobjects.AgreementAsset;
import com.meetyourroommate.app.rentallifecycle.domain.valueobjects.AgreementId;
import com.meetyourroommate.app.shared.valueobjects.Audit;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Agreement {
    @Id
    @GeneratedValue
    @Column(name="id", unique=true, updatable = false)
    private AgreementId agreementId;
    @Embedded
    private AgreementAsset agrementAsset;
    @Column(name="summary_of_terms_and_conditions", updatable = false)
    private String summaryOfTermsAndConditions;
    @Column(name="signed_at", updatable = false)
    private Date signedAt;
    @Column(name="end_at", updatable = false)
    private Date endAt;
    @Embedded
    private Audit audit;
}
