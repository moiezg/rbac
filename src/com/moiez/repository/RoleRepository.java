package com.moiez.repository;

import com.moiez.model.Role;

import java.util.List;

public interface RoleRepository {
    boolean createRole(String roleName);
    boolean removeRole(String roleName);
    Role viewRole(String roleName);

    List<String> listRoles();
}
