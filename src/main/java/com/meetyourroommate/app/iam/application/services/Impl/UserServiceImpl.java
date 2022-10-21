package com.meetyourroommate.app.iam.application.services.Impl;

import com.meetyourroommate.app.iam.application.services.UserService;
import com.meetyourroommate.app.iam.domain.aggregates.Users;
import com.meetyourroommate.app.iam.infrastructure.persistance.jpa.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Users save(Users user) throws Exception {
        return userRepository.save(user);
    }

    @Override
    public List<Users> findAll() throws Exception {
        return userRepository.findAll();
    }

    @Override
    public Optional<Users> findById(String id) throws Exception {
        return userRepository.findById(id);
    }

    @Override
    public Users update(String s, Users user) throws Exception {
        return null;
    }

    @Override
    public void deleteById(String id) throws Exception {
        userRepository.deleteById(id);
    }
}
