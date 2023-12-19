package com.example.demosecurity.service;

import com.example.demosecurity.entity.Role;

import java.util.Set;

public interface RoleService {
    Set<Role> getAll();


    Role getByRoleName(String roleName);

    void addRole(Role role);
}
