package com.remindpay.service;

import com.remindpay.dto.HttpErrorCode;
import com.remindpay.exceptions.BusinessException;
import com.remindpay.model.UserAccount;
import com.remindpay.repository.AccountRepository;
import com.remindpay.repository.UserAccountRepository;
import com.remindpay.repository.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class UserAccountServiceImpl implements UserAccountService{

    @Inject
    UserAccountRepository repository;

    @Inject
    UserRepository userRepository;

    @Inject
    AccountRepository accountRepository;

    @Override
    @Transactional
    public void create(UserAccount userAccount) {

        var user = userRepository.findById(userAccount.getUser().getId());
        if (user == null) {
            throw new BusinessException("Usuário não encontrado.",  HttpErrorCode.UNPROCESSABLE_ENTITY.getCode());
        }

        var account = accountRepository.findById(userAccount.getAccount().getId());
        if (account == null) {
            throw new BusinessException("Conta não encontrada.", HttpErrorCode.UNPROCESSABLE_ENTITY.getCode());
        }

        // Verifica se já existe uma associação entre o usuário e a conta
        var existingAssociation = repository.findByUserAndAccount(user, account);
        if (existingAssociation.isPresent()) {
            throw new BusinessException("Usuário já está vinculado a esta conta.", HttpErrorCode.CONFLICT.getCode());
        }

        repository.persist(userAccount);
    }
}
