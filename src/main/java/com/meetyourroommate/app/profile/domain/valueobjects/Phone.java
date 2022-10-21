package com.meetyourroommate.app.profile.domain.valueobjects;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class Phone implements Serializable {
    private String number;
    private String code;
}
