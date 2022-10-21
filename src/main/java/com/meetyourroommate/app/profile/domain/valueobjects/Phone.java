package com.meetyourroommate.app.profile.domain.valueobjects;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class Phone implements Serializable {
    private String number;
    private String code;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
