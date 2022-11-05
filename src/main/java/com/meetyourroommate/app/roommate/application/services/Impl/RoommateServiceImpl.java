package com.meetyourroommate.app.roommate.application.services.Impl;

import com.meetyourroommate.app.roommate.application.services.RoommateService;
import com.meetyourroommate.app.roommate.domain.entities.Roommate;
import com.meetyourroommate.app.roommate.infrastructure.persistance.jpa.RoommateRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoommateServiceImpl implements RoommateService {

    private RoommateRepository roommateRepository;

    public RoommateServiceImpl(RoommateRepository roommateRepository) {
        this.roommateRepository = roommateRepository;
    }

    @Override
    public Roommate save(Roommate roommate) throws Exception {
        return roommateRepository.save(roommate);
    }

    @Override
    public List<Roommate> findAll() throws Exception {
        return roommateRepository.findAll();
    }

    @Override
    public Optional<Roommate> findById(Long id) throws Exception {
        return roommateRepository.findById(id);
    }

    @Override
    public Roommate update(Long aLong, Roommate roommate) throws Exception {
        return null;
    }

    @Override
    public void deleteById(Long id) throws Exception {
        roommateRepository.deleteById(id);
    }
}
