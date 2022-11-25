package com.meetyourroommate.app.profile.application.transform;

import com.meetyourroommate.app.profile.application.transform.resources.ProfileDto;
import com.meetyourroommate.app.profile.domain.aggregates.Profile;
import com.meetyourroommate.app.shared.application.transform.EnhancedModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ProfileDtoMapper implements Serializable {
    @Autowired
    private EnhancedModelMapper mapper;

    public ProfileDto toDto(Profile model){
        TypeMap<Profile, ProfileDto> typeMap = this.mapper.getTypeMap(Profile.class, ProfileDto.class);
        if(typeMap == null){
            TypeMap<Profile, ProfileDto> propertyMapper = this.mapper.createTypeMap(Profile.class, ProfileDto.class);
            propertyMapper.addMappings(mapp ->
            {
                mapp.map(src -> src.getAudit().getCreatedAt(), ProfileDto::setCreatedAt);
                mapp.map(src -> src.getAtributesSet(), ProfileDto::setAttributes);
            });
        }
        return mapper.map(model, ProfileDto.class);
    }

    public List<ProfileDto> toDtoList(List<Profile> models){
        List<ProfileDto> dtos = new ArrayList<>();
        models.forEach((model) -> {
            dtos.add(mapper.map(model, ProfileDto.class));
        });
        return dtos;
    }
}
