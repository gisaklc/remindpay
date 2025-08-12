package com.remindpay.service;

import com.remindpay.dto.AccessToken;
import com.remindpay.dto.HttpErrorCode;
import com.remindpay.exceptions.BusinessException;
import com.remindpay.model.User;
import com.remindpay.repository.UserRepository;
import com.remindpay.utils.PasswordUtils;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class UserServiceImpl implements UserService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Inject
    UserRepository userRepository;

    @Inject
    JwtService jwtService;


    @Override
    public void create(User user) {

        Optional<User> u = getEmail(user.getEmail());
        if (u.isPresent()) {
            throw new BusinessException("Já existe um usuário com este e-mail cadastrado.", 422);
        }
        String hashedPassword = PasswordUtils.hash(user.getPassword());
        user.setPassword(hashedPassword);
        if (user.getRoles() == null || user.getRoles().isEmpty()) {
             user.setRoles(List.of("USER"));
        }

        userRepository.persist(user);
    }

    @Override
    public AccessToken authenticate(String username, String password) {
        User user = getEmail(username)
                .orElseThrow(() -> new BusinessException("Usuário não encontrado.", HttpErrorCode.UNAUTHORIZED.getCode()));

        //verfica se a senha bate ccom a do banco
        boolean isValid = PasswordUtils.verify(password, user.getPassword());
        if (!isValid) {
            throw new BusinessException("Credenciais inválidas.", HttpErrorCode.UNAUTHORIZED.getCode());
        }

        try {
            return new AccessToken(jwtService.generateToken(user.getEmail(), user.getRoles()));
        } catch (Exception e) {
            log.error("Erro ao gerar token para o usuário {}. Roles: {}",
                    user.getEmail(),
                    user.getRoles(),
                    e);
            throw e; // Propaga para ser tratado na camada superior
        }
    }

    @Override
    public List<User> listAll() {
        return userRepository.listAll();
    }


    private Optional<User> getEmail(String email) {
      return userRepository.findByEmail(email);
    }

}
