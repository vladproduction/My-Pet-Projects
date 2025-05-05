package com.vladproduction.fittrack.mapper.impl;

import com.vladproduction.fittrack.mapper.RowMapper;
import com.vladproduction.fittrack.model.Role;
import com.vladproduction.fittrack.model.User;
import com.vladproduction.fittrack.model.enums.RoleType;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs) throws SQLException {

        User user = new User();
        user.setId(rs.getLong("id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setEmail(rs.getString("email"));
        user.setActive(rs.getBoolean("active"));

        Role role = new Role();
        role.setId(rs.getLong("role_id"));
        role.setRoleType(RoleType.valueOf(rs.getString("role_name"))); // Assumes alias in SQL as `role_name`

        user.setRole(role); // setting full Role

        return user;
    }

    /*To make this work: SQL should join roles and alias the name:
        SELECT u.*, r.id AS role_id, r.name AS role_name
        FROM users u
        JOIN roles r ON u.role_id = r.id*/
}
