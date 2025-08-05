package com.remindpay.service;

import com.remindpay.model.Account;

import java.util.List;
import java.util.UUID;

public interface AccountService {

    void create(Account account);
    List<Account> listAll();
    Account findById(UUID id);
    List<Account> findAccountsByCategoryId(UUID categoryId);

}
