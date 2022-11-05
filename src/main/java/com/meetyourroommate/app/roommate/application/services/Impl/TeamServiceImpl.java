package com.meetyourroommate.app.roommate.application.services.Impl;

import com.meetyourroommate.app.roommate.application.services.TeamService;
import com.meetyourroommate.app.roommate.domain.entities.Team;
import com.meetyourroommate.app.roommate.infrastructure.persistance.jpa.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamServiceImpl implements TeamService {
    private TeamRepository teamRepository;

    public TeamServiceImpl(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    public Team save(Team team) throws Exception {
        return teamRepository.save(team);
    }

    @Override
    public List<Team> findAll() throws Exception {
        return teamRepository.findAll();
    }

    @Override
    public Optional<Team> findById(Long id) throws Exception {
        return teamRepository.findById(id);
    }

    @Override
    public Team update(Long aLong, Team team) throws Exception {
        return null;
    }

    @Override
    public void deleteById(Long id) throws Exception {
        teamRepository.deleteById(id);
    }
}
