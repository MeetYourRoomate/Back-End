package com.meetyourroommate.app.rental.domain.valueobjects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class AgreementAsset implements Serializable {
    @Column(name="urlPdf", unique=true, updatable = false)
    private String urlPdf;
}
