package com.meetyourroommate.app.profile.application.transform.resources;

import com.meetyourroommate.app.profile.domain.valueobjects.Phone;

import javax.persistence.Embedded;

public class ProfileResource {
    private String name;
    private String surname;
    @Embedded
    private Phone phone;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }
}
