package com.meetyourroommate.app.profile.application.transform.resources;

import com.meetyourroommate.app.profile.application.communication.responses.AtributeDtoResponse;
import com.meetyourroommate.app.profile.domain.enumerate.TeamStatus;
import com.meetyourroommate.app.profile.domain.entities.Atribute;
import com.meetyourroommate.app.profile.domain.valueobjects.Phone;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
public class ProfileDto {
    private Long id;
    private String name;
    private String surname;
    private String photoUrl;
    private String gender;
    private String about;
    private TeamStatus teamStatus;
    private String country;
    private String city;
    private Long age;
    private Phone phone;
    private Date createdAt;
    private Set<AtributeDto> atributes;
}
