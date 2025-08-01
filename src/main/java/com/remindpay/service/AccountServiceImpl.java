package com.remindpay.service;

import com.remindpay.dto.HttpErrorCode;
import com.remindpay.exceptions.BusinessException;
import com.remindpay.model.Account;
import com.remindpay.model.Category;
import com.remindpay.model.User;
import com.remindpay.repository.AccountRepository;
import com.remindpay.repository.UserRepository;
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

    @Inject
    UserRepository userRepository;

    @Override
    @Transactional
    public void create(Account account) {

        // 3. Busca o usuário no banco e seta no account (validação importante)
        User user = userRepository.findById(account.getUser().getId());
        if (user == null) {
            throw new BusinessException("Usuário não encontrado.", HttpErrorCode.UNPROCESSABLE_ENTITY.getCode());
        }

        // Verifica se já existe conta com mesmo nome para o mesmo usuário
        Optional<Account> existingAccount = accountRepository.findByNameAndUserId(account.getName(), account.getUser().getId());
        if (existingAccount.isPresent()) {
            throw new BusinessException("Já existe uma conta com esse nome para este usuário.", HttpErrorCode.UNPROCESSABLE_ENTITY.getCode());
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
