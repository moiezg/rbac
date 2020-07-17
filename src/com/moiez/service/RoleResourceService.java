package com.moiez.service;

import com.moiez.model.Resource;
import com.moiez.utils.Constants;

import java.util.*;

import static com.moiez.utils.Constants.*;
import static com.moiez.utils.Constants.RETURN_CODE.*;

public class RoleResourceService {

    RoleService roleService;
    ResourceService resourceService;
    UserRoleService userRoleService;

    Map<String, Map<String, Set<ACTION_TYPE>>> roleResourceActionMap = new HashMap<>();


    public RoleResourceService(UserRoleService userRoleService, RoleService roleService, ResourceService resourceService) {
        this.userRoleService = userRoleService;
        this.roleService = roleService;
        this.resourceService = resourceService;
    }

    public RETURN_CODE grantAccessToResource(String roleName, String resourceName, ACTION_TYPE actionType) {

        if (roleService.getRole(roleName) == null)
            return ROLE_NOT_EXISTS;

        if (resourceService.getResource(resourceName) == null)
            return RESOURCE_NOT_EXISTS;

        if (!roleResourceActionMap.containsKey(roleName))
            roleResourceActionMap.put(roleName, new HashMap<>());

        if (!roleResourceActionMap.get(roleName).containsKey(resourceName))
            roleResourceActionMap.get(roleName).put(resourceName, new HashSet<>());

        roleResourceActionMap.get(roleName).get(resourceName).add(actionType);
        return RESOURCE_ASSIGNED_TO_ROLE;
    }

    public void listResourcesForUser(String userName) {
        Set<String> roles = userRoleService.getRolesForUser().get(userName);
        System.out.println("Resources and their permissions");
        for (String role : roles) {
            System.out.println("ROLE\tRESOURCE\tPermission(s)");
            for (String resource : roleResourceActionMap.get(role).keySet()) {
                System.out.println(role + "\t" + resource + " " + roleResourceActionMap.get(role).get(resource));
            }
        }
    }

    public void listRolesAssignedToUser(String userName) {
        System.out.println("Roles assigned to " + userName + ": " + userRoleService.getRolesForUser().get(userName));
    }

    public void performAction(String userName, String resourceName, ACTION_TYPE actionType) {
        Set<String> roles = userRoleService.getRolesForUser().get(userName);
        boolean hasAccess = false;
        for (String role : roles) {
            if (roleResourceActionMap.get(role).containsKey(resourceName) && roleResourceActionMap.get(role).get(resourceName).contains(actionType)) {
                hasAccess = true;
                break;
            }
        }

        if (hasAccess) {
            Resource resource = resourceService.getResource(resourceName);
            switch (actionType) {
                case READ:
                    resourceService.readResource(resource);
                    break;
                case WRITE:
                    resourceService.writeResource(resource);
                    break;
                case DELETE:
                    resourceService.deleteResource(resourceName);
                    break;
            }
        } else
            System.out.println("Permission Denied");
    }

    public RETURN_CODE removeAccessFromResource(String roleName, String resourceName, ACTION_TYPE actionType) {

        if (roleResourceActionMap.containsKey(roleName) && roleResourceActionMap.get(roleName).containsKey(resourceName) && roleResourceActionMap.get(roleName).get(resourceName).equals(actionType)) {
            roleResourceActionMap.get(roleName).get(resourceName).remove(actionType);
            return SUCCESS;
        } else {
            if (!roleResourceActionMap.containsKey(roleName) || !roleResourceActionMap.get(roleName).containsKey(resourceName))
                return NO_RESOURCE_ASSIGNED_TO_ROLE;
            return ACCESS_NOT_GRANTED;
        }

    }
}
