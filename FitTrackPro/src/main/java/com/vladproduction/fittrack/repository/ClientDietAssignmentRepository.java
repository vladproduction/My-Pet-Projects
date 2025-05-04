package com.vladproduction.fittrack.repository;

import com.vladproduction.fittrack.model.ClientDietAssignment;

import java.util.List;

public interface ClientDietAssignmentRepository {

    List<ClientDietAssignment> findByClientId(Long clientId);
    void save(ClientDietAssignment assignment);
    void deleteByClientId(Long clientId);

}
