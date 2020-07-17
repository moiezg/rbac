package com.moiez.repository;

import com.moiez.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserRepositoryInMemImpl implements UserRepository {
    Map<String, User> users = new HashMap<>();

    @Override
    public boolean createUser(String userName) {

        if (users.containsKey(userName))
            return false;

        User user = new User(userName);
        users.put(userName, user);
        return true;
    }

    @Override
    public boolean removeUser(String userName) {
        if (!users.containsKey(userName))
            return false;

        users.remove(userName);
        return true;
    }

    @Override
    public User viewUser(String userName) {
        return users.get(userName);
    }

    @Override
    public List<String> listUsers() {
        return new ArrayList<>(users.keySet());
    }
}
