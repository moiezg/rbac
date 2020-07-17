package com.moiez.repository;

import com.moiez.model.Role;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoleRepositoryInMemImpl implements RoleRepository{

    Map<String, Role> roles = new HashMap<>();

    @Override
    public boolean createRole(String roleName) {
        if (roles.containsKey(roleName))
            return false;
        roles.put(roleName, new Role(roleName));
        return true;
    }

    @Override
    public boolean removeRole(String roleName) {
        if (!roles.containsKey(roleName))
            return false;
        roles.remove(roleName);
        return true;
    }

    @Override
    public Role viewRole(String roleName) {
        return roles.get(roleName);
    }

    @Override
    public List<String> listRoles() {
        return new ArrayList<>(roles.keySet());
    }
}
