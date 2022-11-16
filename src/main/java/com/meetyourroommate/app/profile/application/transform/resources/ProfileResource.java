package com.meetyourroommate.app.profile.application.transform.resources;

import com.meetyourroommate.app.profile.domain.valueobjects.Phone;
import jdk.jfr.DataAmount;
import lombok.Data;

import javax.persistence.Embedded;

@Data
public class ProfileResource {
    private String name;
    private String surname;
    private String photoUrl;
    private String gender;
    private String about;
    private String location;
    private Phone phone;
}
