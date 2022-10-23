package com.meetyourroommate.app.rental.domain.valueobjects;

import lombok.Data;

import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import java.io.Serializable;

@Embeddable
@Data
public class AgreementId implements Serializable {
    @GeneratedValue
    private Long id;
}
