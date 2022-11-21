package com.meetyourroommate.app.roommate.application.services.Impl;

import com.meetyourroommate.app.profile.domain.aggregates.Profile;
import com.meetyourroommate.app.roommate.application.services.RoommateRequestService;
import com.meetyourroommate.app.roommate.domain.entities.RoommateRequest;
import com.meetyourroommate.app.roommate.infrastructure.persistance.jpa.RoommateRequestRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoommateRequestServiceImpl implements RoommateRequestService {
    private RoommateRequestRepository roommateRequestRepository;

    public RoommateRequestServiceImpl(RoommateRequestRepository roommateRequestRepository) {
        this.roommateRequestRepository = roommateRequestRepository;
    }

    @Override
    public RoommateRequest save(RoommateRequest roommateRequest) throws Exception {
        return roommateRequestRepository.save(roommateRequest);
    }

    @Override
    public List<RoommateRequest> findAll() throws Exception {
        return roommateRequestRepository.findAll();
    }

    @Override
    public Optional<RoommateRequest> findById(Long id) throws Exception {
        return roommateRequestRepository.findById(id);
    }

    @Override
    public RoommateRequest update(Long aLong, RoommateRequest roommateRequest) throws Exception {
        return null;
    }

    @Override
    public void deleteById(Long id) throws Exception {
        roommateRequestRepository.deleteById(id);
    }

    @Override
    public List<RoommateRequest> findAllByStudentRequestor(Profile studentRequestor) throws Exception {
        return roommateRequestRepository.findAllByStudentRequestor(studentRequestor);
    }

    @Override
    public List<RoommateRequest> findAllByStudentRequested(Profile studentRequested) throws Exception {
        return roommateRequestRepository.findAllByStudentRequested(studentRequested);
    }

    @Override
    public Optional<RoommateRequest> findRoommateRequestByStudentRequestedAndStudentRequestor(Profile requested, Profile requestor) {
        return roommateRequestRepository.findRoommateRequestByStudentRequestedAndStudentRequestor(requested, requestor);
    }
}
