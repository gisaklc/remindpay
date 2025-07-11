package com.remindpay.service;

import com.remindpay.dto.HttpErrorCode;
import com.remindpay.exceptions.BusinessException;
import com.remindpay.model.Account;
import com.remindpay.model.Category;
import com.remindpay.repository.AccountRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class AccountServiceImpl implements AccountService {

    @Inject
    AccountRepository accountRepository;

    @Override
    @Transactional
    public void create(Account account) {
        Optional<Account> u = accountRepository.findByName(account.getName());
        if (u.isPresent()) {
            throw new BusinessException("Já existe esta conta cadastrada.",  HttpErrorCode.UNPROCESSABLE_ENTITY.getCode());
        }

        Category category = findCategoryId(account.getCategory().getId());
        if (category == null) {
            throw new BusinessException("Categoria não encontrada.", HttpErrorCode.UNPROCESSABLE_ENTITY.getCode());
        }
        accountRepository.persist(account);
    }

    @Override
    public List<Account> listAll() {
        return accountRepository.listAll();
    }

    private Category findCategoryId(UUID categoryId) {
        return Category.findById(categoryId);
    }

    @Override
    public List<Account> findAccountsByCategoryId(UUID categoryId) {
        Category category = findCategoryId(categoryId);
        if (category == null) {
            throw new BusinessException("Categoria não encontrada.",  HttpErrorCode.UNPROCESSABLE_ENTITY.getCode());
        }

        return accountRepository.findByIdCategory(categoryId);
    }

    @Override
    public Account findById(UUID id) {
        return accountRepository.findById(id);
    }

}
