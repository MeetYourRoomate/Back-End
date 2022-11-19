package com.meetyourroommate.app.profile.application.transform;

import com.meetyourroommate.app.profile.application.transform.resources.AtributeDto;
import com.meetyourroommate.app.profile.domain.entities.Atribute;
import com.meetyourroommate.app.shared.application.transform.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AtributeDtoMapper implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;

    public AtributeDto toDto(Atribute model){
        return mapper.map(model, AtributeDto.class);
    }
    public Atribute toEntity(AtributeDto dto){
        return mapper.map(dto, Atribute.class);
    }
    public List<AtributeDto> toDtoList(List<Atribute> models){
        List<AtributeDto> dtos = new ArrayList<>();
        models.forEach((model) ->{
            dtos.add(mapper.map(model, AtributeDto.class));
        });
        return dtos;
    }
}
