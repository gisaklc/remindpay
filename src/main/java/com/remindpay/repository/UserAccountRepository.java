package com.remindpay.repository;

import com.remindpay.model.Account;
import com.remindpay.model.User;
import com.remindpay.model.UserAccount;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class UserAccountRepository implements PanacheRepository<UserAccount> {

    public List<UserAccount> findByUserId(UUID userId) {
        return find("user.id", userId).list();
    }

    public Optional<UserAccount> findByUserAndAccount(User user, Account account) {
        return find("user = ?1 and account = ?2", user, account).firstResultOptional();
    }
}
