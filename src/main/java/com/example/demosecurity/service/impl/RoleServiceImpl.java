package com.example.demosecurity.service.impl;

import com.example.demosecurity.entity.Role;
import com.example.demosecurity.repository.RoleRepository;
import com.example.demosecurity.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Set<Role> getAll() {
        return new HashSet<>(roleRepository.findAll());
    }

    @Override
    public Role getByRoleName(String roleName) {
        return roleRepository.findByRoleName(roleName);
    }
}
