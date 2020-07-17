package com.moiez.service;

import com.moiez.model.Role;
import com.moiez.repository.RoleRepository;
import com.moiez.repository.RoleRepositoryInMemImpl;

import java.util.List;

public class RoleService {

    RoleRepository roleRepository = new RoleRepositoryInMemImpl();

    public boolean createRole(String roleName) {
        return roleRepository.createRole(roleName);
    }

    public boolean deleteRole(String roleName) {
        return roleRepository.removeRole(roleName);
    }

    public Role getRole(String roleName) {
        return roleRepository.viewRole(roleName);
    }

    public List<String> listRoles() {
        return roleRepository.listRoles();
    }
}
