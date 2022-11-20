package com.meetyourroommate.app.roommate.application.services.Impl;

import com.meetyourroommate.app.roommate.application.services.DutyService;
import com.meetyourroommate.app.roommate.domain.entities.Duty;
import com.meetyourroommate.app.roommate.infrastructure.persistance.jpa.DutyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DutyServiceImpl implements DutyService {
    private final DutyRepository dutyRepository;

    public DutyServiceImpl(DutyRepository dutyRepository) {
        this.dutyRepository = dutyRepository;
    }

    @Override
    public Duty save(Duty duty) throws Exception {
        return dutyRepository.save(duty);
    }

    @Override
    public List<Duty> findAll() throws Exception {
        return dutyRepository.findAll();
    }

    @Override
    public Optional<Duty> findById(String id) throws Exception {
        return dutyRepository.findById(id);
    }

    @Override
    public Duty update(String s, Duty duty) throws Exception {
        return null;
    }

    @Override
    public void deleteById(String id) throws Exception {
        dutyRepository.deleteById(id);
    }
}
