package com.borjafpa.bank.service;

import com.borjafpa.bank.model.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}
