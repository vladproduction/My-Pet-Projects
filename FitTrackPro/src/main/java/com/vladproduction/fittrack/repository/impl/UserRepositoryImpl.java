package com.vladproduction.fittrack.repository.impl;

import com.vladproduction.fittrack.datasource.DBConnection;
import com.vladproduction.fittrack.mapper.impl.UserRowMapper;
import com.vladproduction.fittrack.model.User;
import com.vladproduction.fittrack.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {

    private static final Logger logger = LoggerFactory.getLogger(UserRepositoryImpl.class);
    private final UserRowMapper userRowMapper = new UserRowMapper();

    private static final String BASE_SELECT = """
        SELECT u.id, u.username, u.password, u.email, u.role_id, u.active,
               r.name AS role_name
        FROM users u
        JOIN roles r ON u.role_id = r.id
        """;

    @Override
    public Optional<User> findById(Long id) {
        String query = BASE_SELECT + " WHERE u.id = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    User user = userRowMapper.mapRow(rs);
                    logger.info("Fetched user by ID: {}", id);
                    return Optional.of(user);
                }
            }

        } catch (SQLException e) {
            logger.error("Error fetching user by ID", e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        String query = BASE_SELECT + " WHERE u.email = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    User user = userRowMapper.mapRow(rs);
                    logger.info("Fetched user by email: {}", email);
                    return Optional.of(user);
                }
            }

        } catch (SQLException e) {
            logger.error("Error fetching user by email", e);
        }
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(BASE_SELECT);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                users.add(userRowMapper.mapRow(rs));
            }
            logger.info("Fetched {} users", users.size());

        } catch (SQLException e) {
            logger.error("Error fetching all users", e);
        }
        return users;
    }

    @Override
    public void save(User user) {
        String query = """
            INSERT INTO users (username, password, email, role_id, active)
            VALUES (?, ?, ?, ?, ?)
            """;
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getEmail());
            ps.setLong(4, user.getRole().getId());
            ps.setBoolean(5, user.isActive());

            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    user.setId(keys.getLong(1));
                }
            }

            logger.info("Saved new user: {}", user.getUsername());

        } catch (SQLException e) {
            logger.error("Error saving user", e);
        }
    }

    @Override
    public void update(User user) {
        String query = """
            UPDATE users
            SET username = ?, password = ?, email = ?, role_id = ?, active = ?
            WHERE id = ?
            """;
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getEmail());
            ps.setLong(4, user.getRole().getId());
            ps.setBoolean(5, user.isActive());
            ps.setLong(6, user.getId());

            ps.executeUpdate();
            logger.info("Updated user: {}", user.getUsername());

        } catch (SQLException e) {
            logger.error("Error updating user", e);
        }
    }

    @Override
    public void delete(Long id) {
        String query = "DELETE FROM users WHERE id = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setLong(1, id);
            ps.executeUpdate();
            logger.info("Deleted user with ID: {}", id);

        } catch (SQLException e) {
            logger.error("Error deleting user", e);
        }
    }

}
