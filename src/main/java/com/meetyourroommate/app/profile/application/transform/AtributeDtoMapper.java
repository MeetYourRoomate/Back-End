package com.meetyourroommate.app.profile.application.transform;

import com.meetyourroommate.app.profile.application.transform.resources.AttributeDto;
import com.meetyourroommate.app.profile.domain.entities.Attribute;
import com.meetyourroommate.app.shared.application.transform.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class AtributeDtoMapper implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;

    public AttributeDto toDto(Attribute model){
        return mapper.map(model, AttributeDto.class);
    }
    public Attribute toEntity(AttributeDto dto){
        return mapper.map(dto, Attribute.class);
    }
    public List<AttributeDto> toDtoList(Set<Attribute> models){
        List<AttributeDto> dtos = new ArrayList<>();
        models.forEach((model) ->{
            dtos.add(mapper.map(model, AttributeDto.class));
        });
        return dtos;
    }

    public List<AttributeDto> toDtoList(List<Attribute> models){
        List<AttributeDto> dtos = new ArrayList<>();
        models.forEach((model) ->{
            dtos.add(mapper.map(model, AttributeDto.class));
        });
        return dtos;
    }
}
