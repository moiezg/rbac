package com.moiez.service;

import com.moiez.model.Role;
import com.moiez.model.User;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static com.moiez.utils.Constants.*;
import static com.moiez.utils.Constants.RETURN_CODE.*;


public class UserRoleService {

    UserService userService;
    RoleService roleService;

    Map<String, Set<String>> usersInRole = new HashMap<>();
    Map<String, Set<String>> rolesForUser = new HashMap<>();

    public Map<String, Set<String>> getUsersInRole() {
        return usersInRole;
    }

    public Map<String, Set<String>> getRolesForUser() {
        return rolesForUser;
    }

    public UserRoleService(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    public RETURN_CODE addUserToRole(String userName, String roleName) {

        if (usersInRole.containsKey(roleName) && usersInRole.get(roleName).contains(userName))
            return ROLE_ALREADY_ASSIGNED;

        User user = userService.getUser(userName);

        if (user == null)
            return USER_NOT_EXISTS;

        Role role = roleService.getRole(roleName);

        if (role == null)
            return ROLE_NOT_EXISTS;

        if (!usersInRole.containsKey(roleName))
            usersInRole.put(roleName, new HashSet<>());

        usersInRole.get(roleName).add(userName);

        if (!rolesForUser.containsKey(userName))
            rolesForUser.put(userName, new HashSet<>());

        rolesForUser.get(userName).add(roleName);

        return ROLE_ASSIGNED_TO_USER;
    }

    public RETURN_CODE removeUserFromRole(String userName, String roleName) {
        if (!usersInRole.containsKey(roleName) || !usersInRole.get(roleName).contains(userName))
            return ROLE_NOT_ASSIGNED;

        User user = userService.getUser(userName);

        if (user == null)
            return USER_NOT_EXISTS;

        Role role = roleService.getRole(roleName);

        if (role == null)
            return ROLE_NOT_EXISTS;


        usersInRole.get(roleName).remove(userName);
        rolesForUser.get(userName).remove(roleName);

        return SUCCESS;
    }
}
