package com.meetyourroommate.app.profile.application.transform.resources;

import com.meetyourroommate.app.profile.domain.valueobjects.Phone;
import lombok.Data;


@Data
public class ProfileResource {
    private String name;
    private String surname;
    private String photoUrl;
    private String gender;
    private String about;
    private String country;
    private String city;
    private Long age;
    private Phone phone;
}
