package com.moiez.service;

import com.moiez.model.User;
import com.moiez.repository.UserRepository;
import com.moiez.repository.UserRepositoryInMemImpl;

import java.util.List;

public class UserService {

    UserRepository userRepository = new UserRepositoryInMemImpl();

    public boolean createUser(String userName) {
        return userRepository.createUser(userName);
    }

    public boolean deleteUser(String userName) {
        return userRepository.removeUser(userName);
    }

    public User getUser(String userName) {
        return userRepository.viewUser(userName);
    }

    public List<String> listUsers(){
        return userRepository.listUsers();
    }

}
