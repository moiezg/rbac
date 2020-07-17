package com.moiez.utils;

public class Constants {
    public enum ACTION_TYPE {
        READ,
        WRITE,
        DELETE
    }

    public enum RETURN_CODE {
        ROLE_EXISTS,
        USER_EXISTS,
        RESOURCE_EXISTS,
        ROLE_NOT_EXISTS,
        USER_NOT_EXISTS,
        RESOURCE_NOT_EXISTS,
        ROLE_ALREADY_ASSIGNED,
        ROLE_NOT_ASSIGNED,
        ROLE_ASSIGNED_TO_USER,
        RESOURCE_ASSIGNED_TO_ROLE,
        NO_RESOURCE_ASSIGNED_TO_ROLE,
        PERMISSION_DENIED,
        SUCCESS,
        ACCESS_NOT_GRANTED
    }

    public static ACTION_TYPE fromInteger(int x) {
        switch(x) {
            case 0:
                return ACTION_TYPE.READ;
            case 1:
                return ACTION_TYPE.WRITE;
            case 2:
                return ACTION_TYPE.DELETE;
        }
        return null;
    }

}
