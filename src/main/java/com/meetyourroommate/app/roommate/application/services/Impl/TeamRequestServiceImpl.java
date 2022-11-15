package com.meetyourroommate.app.roommate.application.services.Impl;

import com.meetyourroommate.app.roommate.application.services.TeamRequestService;
import com.meetyourroommate.app.roommate.domain.entities.Team;
import com.meetyourroommate.app.roommate.domain.entities.TeamRequest;
import com.meetyourroommate.app.roommate.infrastructure.persistance.jpa.TeamRequestRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamRequestServiceImpl implements TeamRequestService {
    private TeamRequestRepository teamRequestRepository;

    public TeamRequestServiceImpl(TeamRequestRepository teamRequestRepository) {
        this.teamRequestRepository = teamRequestRepository;
    }

    @Override
    public TeamRequest save(TeamRequest teamRequest) throws Exception {
        return teamRequestRepository.save(teamRequest);
    }

    @Override
    public List<TeamRequest> findAll() throws Exception {
        return teamRequestRepository.findAll();
    }

    @Override
    public Optional<TeamRequest> findById(String id) throws Exception {
        return teamRequestRepository.findById(id);
    }

    @Override
    public TeamRequest update(String s, TeamRequest teamRequest) throws Exception {
        return null;
    }

    @Override
    public void deleteById(String id) throws Exception {
        teamRequestRepository.deleteById(id);
    }

    @Override
    public List<TeamRequest> findAllByTeamRequested(Team team) {
        return teamRequestRepository.findAllByTeamRequested(team);
    }
}
