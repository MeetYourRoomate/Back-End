package com.meetyourroommate.app.profile.application.services.Impl;

import com.meetyourroommate.app.iam.domain.aggregates.User;
import com.meetyourroommate.app.iam.infrastructure.persistance.jpa.UserRepository;
import com.meetyourroommate.app.profile.application.services.ProfileService;
import com.meetyourroommate.app.profile.domain.aggregates.Profile;
import com.meetyourroommate.app.profile.domain.enumerate.TeamStatus;
import com.meetyourroommate.app.profile.infrastructure.persistance.jpa.ProfileRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfileServiceImpl implements ProfileService {
    private ProfileRepository profileRepository;
    private UserRepository userRepository;

    public ProfileServiceImpl(ProfileRepository profileRepository, UserRepository userRepository) {
        this.profileRepository = profileRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Profile save(Profile profile) throws Exception {
        return profileRepository.save(profile);
    }

    @Override
    public List<Profile> findAll() throws Exception {
        return profileRepository.findAll();
    }

    @Override
    public Optional<Profile> findById(Long id) throws Exception {
        return profileRepository.findById(id);
    }

    @Override
    public Profile update(Long aLong, Profile profile) throws Exception {
        return null;
    }

    @Override
    public void deleteById(Long id) throws Exception {
        profileRepository.deleteById(id);
    }

    @Override
    public Optional<Profile> findByUser(User user) {
        return profileRepository.findByUser(user);
    }

    @Override
    public Optional<Profile> findByUserId(String userId) {
        Optional<User> user = userRepository.findById(userId);
        if(user.isEmpty()){
           return Optional.empty();
        }
        Optional<Profile> profile = profileRepository.findByUser(user.get());
        if(profile.isEmpty()){
            return Optional.empty();
        }
        return profile;
    }

    @Override
    public List<Profile> findByTeamStatus(TeamStatus teamStatus) {
        return profileRepository.findAllByTeamStatus(teamStatus);
    }
}
