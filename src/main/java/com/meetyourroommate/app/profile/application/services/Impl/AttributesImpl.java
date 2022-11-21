package com.meetyourroommate.app.profile.application.services.Impl;

import com.meetyourroommate.app.profile.application.services.AttributeService;
import com.meetyourroommate.app.profile.domain.entities.Attribute;
import com.meetyourroommate.app.profile.infrastructure.persistance.jpa.AtributeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AttributesImpl implements AttributeService {
    private final AtributeRepository atributeRepository;

    public AttributesImpl(AtributeRepository atributeRepository) {
        this.atributeRepository = atributeRepository;
    }

    @Override
    public Attribute save(Attribute attribute) throws Exception {
        return atributeRepository.save(attribute);
    }

    @Override
    public List<Attribute> findAll() throws Exception {
        return atributeRepository.findAll();
    }

    @Override
    public Optional<Attribute> findById(String id) throws Exception {
        return atributeRepository.findById(id);
    }

    @Override
    public Attribute update(String s, Attribute attribute) throws Exception {
        return null;
    }

    @Override
    public void deleteById(String id) throws Exception {
        atributeRepository.deleteById(id);
    }
}
