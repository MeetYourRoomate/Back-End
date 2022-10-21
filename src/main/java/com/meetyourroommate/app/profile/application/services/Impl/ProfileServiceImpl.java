package com.meetyourroommate.app.profile.application.services.Impl;

import com.meetyourroommate.app.iam.domain.aggregates.User;
import com.meetyourroommate.app.profile.application.services.ProfileService;
import com.meetyourroommate.app.profile.domain.aggregates.Profile;
import com.meetyourroommate.app.profile.infrastructure.persistance.jpa.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfileServiceImpl implements ProfileService {
    @Autowired
    private ProfileRepository profileRepository;
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
    public Profile findByUser(User user) {
        return profileRepository.findByUser(user);
    }
}
