package com.example.demosecurity.repository;

import com.example.demosecurity.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RoleRepository extends JpaRepository<Role, Long> {
    @Query("select r from Role r where lower(r.role) like :role")
    Role findByRoleName(@Param("role") String role);

}
