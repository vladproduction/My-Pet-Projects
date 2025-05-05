package com.vladproduction.fittrack.repository.impl;

import com.vladproduction.fittrack.datasource.DBConnection;
import com.vladproduction.fittrack.mapper.impl.RoleRowMapper;
import com.vladproduction.fittrack.model.Role;
import com.vladproduction.fittrack.repository.RoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class RoleRepositoryImpl implements RoleRepository {

    private static final Logger logger = LoggerFactory.getLogger(RoleRepositoryImpl.class);
    private final RoleRowMapper roleRowMapper = new RoleRowMapper();


    @Override
    public Optional<Role> findById(Long id) {
        String query = "SELECT * FROM roles WHERE id = ?";
        try(Connection connection = DBConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(query)){

            ps.setLong(1, id); // ✅ First set the parameter
            ResultSet rs = ps.executeQuery(); // ✅ Then execute
            if(rs.next()){
                Role role = roleRowMapper.mapRow(rs);
                logger.info("Fetched role: {} by id: {}", role, id);
                return Optional.of(role);
            }
        }catch (SQLException e){
            logger.error("Error fetching role by id: {}", e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public Optional<Role> findByType(String roleType) {
        String query = "SELECT * FROM roles WHERE name = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, roleType);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Role role = roleRowMapper.mapRow(rs);
                logger.info("Fetched role by type: {}", roleType);
                return Optional.of(role);
            }
        } catch (SQLException e) {
            logger.error("Error fetching role by type", e);
        }
        return Optional.empty();
    }

    @Override
    public List<Role> findAll() {
        String query = "SELECT * FROM roles";
        List<Role> roles = new ArrayList<>();
        try(Connection connection = DBConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(query)){

            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                roles.add(roleRowMapper.mapRow(rs));
                logger.info("Fetched roles: {}", roles.get(roles.size()-1));
            }
        }catch (SQLException e){
            logger.error("Error fetching all roles", e);
        }
        return roles; // ✅ Return what was collected
    }

    @Override
    public void save(Role role) {
        String query = "INSERT INTO roles (name) VALUES (?)";
        try(Connection connection = DBConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){

            ps.setString(1, role.getRoleType().name());
            ps.executeUpdate();

            ResultSet keys = ps.getGeneratedKeys();
            if(keys.next()){
                role.setId(keys.getLong(1));
            }
            Long id = role.getId();
            if(id != null){
                logger.info("Saved new role: {} with id: {}", role, id);
            }

        }catch (SQLException e){
            logger.error("Error saving new role", e);
        }
    }

    @Override
    public void update(Role role) {
        String query = "UPDATE roles SET name = ? WHERE id = ?";
        try(Connection connection = DBConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(query)){

            ps.setString(1, role.getRoleType().name());
            ps.setLong(2, role.getId());
            ps.executeUpdate();
            logger.info("Updated role: {}", role);

        }catch (SQLException e){
            logger.error("Error updating role", e);
        }
    }

    @Override
    public void delete(Long id) {
        String query = "DELETE FROM roles WHERE id = ?";
        try(Connection connection = DBConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(query)){

            ps.setLong(1, id);
            ps.executeUpdate();
            logger.info("Deleted role with id: {}", id);
        }catch (SQLException e){
            logger.error("Error deleting role", e);
        }
    }

}
