package com.meetyourroommate.app.iam.application.services.Impl;

import com.meetyourroommate.app.iam.application.services.UserService;
import com.meetyourroommate.app.iam.domain.aggregates.User;
import com.meetyourroommate.app.iam.infrastructure.persistance.jpa.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public User save(User user) throws Exception {
        return userRepository.save(user);
    }

    @Override
    public List<User> findAll() throws Exception {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Long id) throws Exception {
        return userRepository.findById(id);
    }

    @Override
    public User update(Long id, User user) throws Exception {
        return null;
    }

    @Override
    public void deleteById(Long id) throws Exception {
        userRepository.deleteById(id);
    }
}
