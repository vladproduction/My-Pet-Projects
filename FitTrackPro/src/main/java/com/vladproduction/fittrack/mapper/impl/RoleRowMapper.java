package com.vladproduction.fittrack.mapper.impl;

import com.vladproduction.fittrack.mapper.RowMapper;
import com.vladproduction.fittrack.model.Role;
import com.vladproduction.fittrack.model.enums.RoleType;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleRowMapper implements RowMapper<Role> {

    @Override
    public Role mapRow(ResultSet rs) throws SQLException {
        Role role = new Role();
        role.setId(rs.getLong("id"));
        String roleName = rs.getString("name");
        role.setRoleType(RoleType.fromString(roleName));
        return role;
    }

}
