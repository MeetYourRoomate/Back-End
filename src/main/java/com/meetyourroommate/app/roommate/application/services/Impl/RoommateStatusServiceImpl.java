package com.meetyourroommate.app.roommate.application.services.Impl;

import com.meetyourroommate.app.roommate.application.services.RoommateStatusService;
import com.meetyourroommate.app.roommate.domain.entities.Roommate;
import com.meetyourroommate.app.roommate.domain.entities.RoommateStatus;
import com.meetyourroommate.app.roommate.domain.entities.TeamRequest;
import com.meetyourroommate.app.roommate.infrastructure.persistance.jpa.RoommateStatusRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoommateStatusServiceImpl implements RoommateStatusService {
    private RoommateStatusRepository roommateStatusRepository;

    public RoommateStatusServiceImpl(RoommateStatusRepository roommateStatusRepository) {
        this.roommateStatusRepository = roommateStatusRepository;
    }

    @Override
    public RoommateStatus save(RoommateStatus roommateStatus) throws Exception {
        return roommateStatusRepository.save(roommateStatus);
    }

    @Override
    public List<RoommateStatus> findAll() throws Exception {
        return roommateStatusRepository.findAll();
    }

    @Override
    public Optional<RoommateStatus> findById(String id) throws Exception {
        return  roommateStatusRepository.findById(id);
    }

    @Override
    public RoommateStatus update(String s, RoommateStatus roommateStatus) throws Exception {
        return null;
    }

    @Override
    public void deleteById(String id) throws Exception {
        roommateStatusRepository.deleteById(id);
    }

    @Override
    public List<RoommateStatus> findRoommateStatusesByTeamRequest(TeamRequest teamRequest) {
        return roommateStatusRepository.findRoommateStatusesByTeamRequest(teamRequest);
    }

    @Override
    public Optional<RoommateStatus> findRoommateStatusByRoommateAndTeamRequest(Roommate roommate, TeamRequest teamRequest) {
        return roommateStatusRepository.findRoommateStatusByRoommateAndTeamRequest(roommate,teamRequest);
    }
}
