package com.remindpay.repository;

import com.remindpay.model.Account;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class AccountRepository implements PanacheRepositoryBase<Account, UUID> {

    public Optional<Account> findByName(String name) {
        return find("name", name).firstResultOptional();
    }

    public List<Account> findByIdCategory(UUID categoryId) {
        return find("category.id", categoryId).list();
    }

    // Implementação do método que busca por nome e userId
    public Optional<Account> findByNameAndUserId(String accountName, UUID userId) {
        return find("name = ?1 and user.id = ?2", accountName, userId).firstResultOptional();
    }
}
