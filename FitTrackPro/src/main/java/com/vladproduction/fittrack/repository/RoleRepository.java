package com.vladproduction.fittrack.repository;

import com.vladproduction.fittrack.model.Role;

import java.util.List;
import java.util.Optional;

public interface RoleRepository {

    Optional<Role> findById(Long id);
    Optional<Role> findByType(String roleType);
    List<Role> findAll();
    void save(Role role);
    void update(Role role);
    void delete(Long id);

}
