package com.vladproduction.fittrack.repository;

import com.vladproduction.fittrack.model.Coach;

import java.util.List;
import java.util.Optional;

public interface CoachRepository {

    Optional<Coach> findById(Long id);
    List<Coach> findAll();
    void save(Coach coach);
    void update(Coach coach);
    void delete(Long id);

}
