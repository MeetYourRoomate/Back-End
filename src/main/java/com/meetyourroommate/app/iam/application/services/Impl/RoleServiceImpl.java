package com.meetyourroommate.app.iam.application.services.Impl;

import com.meetyourroommate.app.iam.application.services.RoleService;
import com.meetyourroommate.app.iam.domain.entities.Role;
import com.meetyourroommate.app.iam.domain.entities.enums.Roles;
import com.meetyourroommate.app.iam.infrastructure.persistance.jpa.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {
    private RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role save(Role role) throws Exception {
        return roleRepository.save(role);
    }

    @Override
    public List<Role> findAll() throws Exception {
        return roleRepository.findAll();
    }

    @Override
    public Optional<Role> findById(Long id) throws Exception {
        return roleRepository.findById(id);
    }

    @Override
    public Role update(Long aLong, Role role) throws Exception {
        return null;
    }

    @Override
    public void deleteById(Long id) throws Exception {
        roleRepository.deleteById(id);
    }

    @Override
    public Optional<Role> findByName(Roles roleName) {
        return roleRepository.findByName(roleName);
    }
}
