package com.meetyourroommate.app.profile.domain.valueobjects;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class Phone {
    private String number;
    private String code;

    public String getNumber() {
        return number;
    }

    public Phone setNumber(String number) {
        this.number = number;
        return this;
    }

    public String getCode() {
        return code;
    }

    public Phone setCode(String code) {
        this.code = code;
        return this;
    }
}
