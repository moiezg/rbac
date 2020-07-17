package com.moiez;

import com.moiez.service.*;
import com.moiez.utils.Constants;

import java.util.Scanner;

import static com.moiez.utils.Constants.*;

public class RBACApplication {

    static Scanner scanner = new Scanner(System.in);

    RoleService roleService = new RoleService();
    UserService userService = new UserService();
    ResourceService resourceService = new ResourceService();
    UserRoleService userRoleService = new UserRoleService(userService, roleService);
    RoleResourceService roleResourceService = new RoleResourceService(userRoleService, roleService, resourceService);

    public static void main(String[] args) {
        RBACApplication rbacApplication = new RBACApplication();
        rbacApplication.showMainMenu();
    }

    private void showMainMenu() {

        System.out.println("Enter User Name: ");
        String userName = scanner.nextLine();
        if (userName.equalsIgnoreCase("admin")) {
            this.showAdminMenu();
        } else {
            this.showUserMenu(userName);
        }
    }

    private void showAdminMenu() {
        System.out.println("Choose an option from below");
        System.out.println("1. Create/Delete User");
        System.out.println("2. Create/Delete Role");
        System.out.println("3. Create/Delete Resource");
        System.out.println("4. List All Users");
        System.out.println("5. List Roles");
        System.out.println("6. List Resources");
        System.out.println("7. List Users in Role");
        System.out.println("8. List roles assigned to user");
        System.out.println("9. Assign role to user");
        System.out.println("10. Remove user from role");
        System.out.println("11. Add role to resource");
        System.out.println("12. Remove role from resource");
        System.out.println("13. Back to main menu");
        System.out.println("14. Exit");

        int option = scanner.nextInt();

        switch (option) {
            case 1:
                showUserCrudMenu();
                break;
            case 2:
                showRoleCrudMenu();
                break;
            case 3:
                showResourceCrudMenu();
                break;
            case 4:
                listUsers();
                showAdminMenu();
                break;
            case 5:
                listRoles();
                showAdminMenu();
                break;
            case 6:
                listResources();
                showAdminMenu();
                break;
            case 7:
                listUsersForRole();
                showAdminMenu();
                break;
            case 8:
                listRolesForUser();
                showAdminMenu();
                break;
            case 9:
                addUserToRole();
                break;
            case 10:
                removeUserFromRole();
                break;
            case 11:
                grantAccessToResource();
                break;

            case 12:
                removeAccessFromResource();
                break;

            case 13:
                showMainMenu();
                break;

            case 14:
                System.exit(0);

            default:
                System.out.println("Invalid Option");
                showAdminMenu();
        }
    }

    private void listRolesForUser() {
        System.out.println("User: ");
        String userName = scanner.nextLine();
        System.out.println(userRoleService.getRolesForUser().get(userName));
    }

    private void listUsersForRole() {
        System.out.println("Role: ");
        String roleName = scanner.next();
        System.out.println(userRoleService.getUsersInRole().get(roleName));

    }

    private void listResources() {
        System.out.println(resourceService.listResources());
    }


    private void listRoles() {
        System.out.println(roleService.listRoles());
    }

    private void listUsers() {
        System.out.println(userService.listUsers());
    }

    private void showUserCrudMenu() {
        System.out.println("Select an option");
        System.out.println("1. Create a user");
        System.out.println("2. Delete a user");
        System.out.println("3. Back to previous menu");
        System.out.println("4. Exit");

        int option = scanner.nextInt();
        String userName;
        switch (option) {
            case 1:
                System.out.println("Enter User Name: ");
                userName = scanner.next();
                if (!userService.createUser(userName))
                    System.out.println("This user already exists");
                else
                    System.out.println("User created");
                showUserCrudMenu();
                break;

            case 2:
                System.out.println("Enter User Name: ");
                userName = scanner.next();
                if (!userService.deleteUser(userName))
                    System.out.println("This user does not exist");
                else
                    System.out.println("User deleted");
                showUserCrudMenu();
                break;

            case 3:
                showAdminMenu();
                break;

            case 4:
                System.out.println("Exiting");
                System.exit(0);

            default:
                System.out.println("Invalid option");
                showUserCrudMenu();

        }
    }

    private void showRoleCrudMenu() {
        System.out.println("Select an option");
        System.out.println("1. Create a role");
        System.out.println("2. Delete a role");
        System.out.println("3. Back to previous menu");
        System.out.println("4. Exit");

        int option = scanner.nextInt();
        String roleName;
        switch (option) {
            case 1:
                System.out.println("Enter Role Name: ");
                roleName = scanner.next();
                if (!roleService.createRole(roleName))
                    System.out.println("This role already exists");
                showRoleCrudMenu();
                break;

            case 2:
                roleName = scanner.next();
                if (!roleService.deleteRole(roleName))
                    System.out.println("This role does not exist");
                break;

            case 3:
                this.showAdminMenu();
                break;

            case 4:
                System.out.println("Exiting");
                System.exit(0);

            default:
                System.out.println("Enter a valid option");
                this.showRoleCrudMenu();

        }

    }

    private void showResourceCrudMenu() {
        System.out.println("Select an option");
        System.out.println("1. Create a resource");
        System.out.println("2. Delete a resource");
        System.out.println("3. Back to previous menu");
        System.out.println("4. Exit");

        int option = scanner.nextInt();
        String resourceName;
        switch (option) {
            case 1:
                System.out.println("Enter Resource Name: ");
                resourceName = scanner.next();
                if (!resourceService.createResource(resourceName))
                    System.out.println("This resource already exists");
                showResourceCrudMenu();
                break;

            case 2:
                resourceName = scanner.next();
                if (!resourceService.deleteResource(resourceName))
                    System.out.println("This resource does not exist");
                showResourceCrudMenu();
                break;

            case 3:
                showAdminMenu();
                break;

            case 4:
                System.out.println("Exiting");
                System.exit(0);

            default:
                System.out.println("Enter a valid option");
                this.showUserCrudMenu();

        }

    }

    private void removeAccessFromResource() {

        System.out.println("Role: ");
        String roleName = scanner.nextLine();
        System.out.println("Resource: ");
        String resourceName = scanner.nextLine();
        System.out.println("Action: ");
        ACTION_TYPE actionType = Constants.fromInteger(scanner.nextInt());

        RETURN_CODE returnCode = roleResourceService.removeAccessFromResource(roleName, resourceName, actionType);

        if (returnCode.equals(RETURN_CODE.SUCCESS))
            System.out.println("Access to resource removed for role");
        else
            System.out.println("Couldn't remove access to resource");

        showAdminMenu();
    }

    private void grantAccessToResource() {
        System.out.println("Role: ");
        String roleName = scanner.nextLine();
        System.out.println("Resource: ");
        String resourceName = scanner.nextLine();
        System.out.println("Action: ");
        ACTION_TYPE actionType = Constants.fromInteger(scanner.nextInt());


        RETURN_CODE returnCode = roleResourceService.grantAccessToResource(roleName, resourceName, actionType);

        switch (returnCode) {
            case ROLE_NOT_EXISTS:
                System.out.println("Role does not exist. Access not granted to resource.");
                break;
            case RESOURCE_NOT_EXISTS:
                System.out.println("Resource does not exist. Access not granted to resource.");
                break;
            case RESOURCE_ASSIGNED_TO_ROLE:
                System.out.println("Access granted to resource");
                break;
        }
        showAdminMenu();
    }

    private void addUserToRole() {
        System.out.println("User: ");
        String userName = scanner.nextLine();
        System.out.println("Role: ");
        String roleName = scanner.nextLine();

        RETURN_CODE returnCode = userRoleService.addUserToRole(userName, roleName);

        switch (returnCode) {
            case ROLE_ASSIGNED_TO_USER:
                System.out.println("Role assigned to user");
                break;
            case ROLE_NOT_EXISTS:
                System.out.println("Role does not exist");
                break;
            case USER_NOT_EXISTS:
                System.out.println("User does not exist");
                break;
            case ROLE_ALREADY_ASSIGNED:
                System.out.println("Role already assigned to user. Not assigning again.");
                break;
        }
        showAdminMenu();

    }

    private void removeUserFromRole() {
        System.out.println("User: ");
        String userName = scanner.next();
        System.out.println("Role: ");
        String roleName = scanner.next();
        RETURN_CODE returnCode = userRoleService.removeUserFromRole(userName, roleName);

        switch (returnCode) {
            case ROLE_NOT_EXISTS:
                System.out.println("Role does not exist");
                break;

            case USER_NOT_EXISTS:
                System.out.println("User does not exist");
                break;

            case ROLE_NOT_ASSIGNED:
                System.out.println("No user assigned to this role");
                break;

            case SUCCESS:
                System.out.println("User removed from role");
        }

        showAdminMenu();
    }

    private void showUserMenu(String userName) {
        System.out.println("Select an option");
        System.out.println("1. List resources");
        System.out.println("2. View assigned roles");
        System.out.println("3. Read from a resource");
        System.out.println("4. Write to a resource");
        System.out.println("5. Delete a resource");
        System.out.println("6. Back to previous menu");
        System.out.println("7. Exit");

        int option = scanner.nextInt();

        switch (option) {
            case 1:
                roleResourceService.listResourcesForUser(userName);
                break;
            case 2:
                roleResourceService.listRolesAssignedToUser(userName);
                break;
            case 3:
                System.out.println("Resource: ");
                String resourceName = scanner.nextLine();
                roleResourceService.performAction(userName, resourceName, ACTION_TYPE.READ);
                break;
            case 4:
                System.out.println("Resource: ");
                resourceName = scanner.nextLine();
                roleResourceService.performAction(userName, resourceName, ACTION_TYPE.WRITE);
                break;
            case 5:
                System.out.println("Resource: ");
                resourceName = scanner.nextLine();
                roleResourceService.performAction(userName, resourceName, ACTION_TYPE.DELETE);
                break;
            case 6:
                this.showMainMenu();
                break;
            case 7:
                System.exit(0);

        }

    }

}
