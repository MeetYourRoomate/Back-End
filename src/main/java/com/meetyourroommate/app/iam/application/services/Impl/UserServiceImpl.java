package com.meetyourroommate.app.iam.application.services.Impl;

import com.meetyourroommate.app.iam.application.services.UserService;
import com.meetyourroommate.app.iam.domain.aggregates.User;
import com.meetyourroommate.app.iam.domain.valueobjects.Email;
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
    public User save(User user) throws Exception {
        return userRepository.save(user);
    }

    @Override
    public List<User> findAll() throws Exception {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(String id) throws Exception {
        return userRepository.findById(id);
    }

    @Override
    public User update(String s, User user) throws Exception {
        return null;
    }

    @Override
    public void deleteById(String id) throws Exception {
        userRepository.deleteById(id);
    }

    @Override
    public Optional<User> findUserByEmail(Email email) {
        return userRepository.findByEmail(email);
    }
}
