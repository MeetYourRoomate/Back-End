package com.meetyourroommate.app.rentallifecycle.domain.valueobjects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class AgrementAsset implements Serializable {
    @Column(name="urlPdf", unique=true, updatable = false)
    private String urlPdf;
}
