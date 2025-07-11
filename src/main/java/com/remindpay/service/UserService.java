package com.remindpay.service;

import com.remindpay.dto.AccessToken;
import com.remindpay.model.User;

public interface UserService {

    void create(User user);

    AccessToken authenticate(String username, String password);

}
