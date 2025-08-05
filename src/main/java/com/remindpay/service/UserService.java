package com.remindpay.service;

import com.remindpay.dto.AccessToken;
import com.remindpay.model.User;

import java.util.List;

public interface UserService {

    void create(User user);

    AccessToken authenticate(String username, String password);

    List<User> listAll();

}
