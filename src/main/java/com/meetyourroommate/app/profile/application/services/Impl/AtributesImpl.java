package com.meetyourroommate.app.profile.application.services.Impl;

import com.meetyourroommate.app.profile.application.services.AtributeService;
import com.meetyourroommate.app.profile.domain.entities.Atribute;
import com.meetyourroommate.app.profile.infrastructure.persistance.jpa.AtributeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AtributesImpl implements AtributeService {
    private final AtributeRepository atributeRepository;

    public AtributesImpl(AtributeRepository atributeRepository) {
        this.atributeRepository = atributeRepository;
    }

    @Override
    public Atribute save(Atribute atribute) throws Exception {
        return atributeRepository.save(atribute);
    }

    @Override
    public List<Atribute> findAll() throws Exception {
        return atributeRepository.findAll();
    }

    @Override
    public Optional<Atribute> findById(String id) throws Exception {
        return atributeRepository.findById(id);
    }

    @Override
    public Atribute update(String s, Atribute atribute) throws Exception {
        return null;
    }

    @Override
    public void deleteById(String id) throws Exception {
        atributeRepository.deleteById(id);
    }
}
