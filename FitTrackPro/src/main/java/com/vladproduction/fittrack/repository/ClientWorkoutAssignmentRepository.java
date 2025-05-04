package com.vladproduction.fittrack.repository;

import com.vladproduction.fittrack.model.ClientWorkoutAssignment;

import java.util.List;

public interface ClientWorkoutAssignmentRepository {

    List<ClientWorkoutAssignment> findByClientId(Long clientId);
    void save(ClientWorkoutAssignment assignment);
    void deleteByClientId(Long clientId);

}
