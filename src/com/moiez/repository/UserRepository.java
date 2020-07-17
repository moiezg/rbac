package com.moiez.repository;

import com.moiez.model.User;

import java.util.List;

public interface UserRepository {
    boolean createUser(String userName);
    boolean removeUser(String userName);
    User viewUser(String userName);

    List<String> listUsers();

}
