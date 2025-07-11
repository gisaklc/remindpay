package com.remindpay.service;

import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class JwtService {

    public String generateToken(String email, List<String> roles) {
        return Jwt.issuer("remindpay") // caminho
                .upn(email)
                .claim("groups", roles)
                .expiresIn(3600) // 1 hora
                .sign(); // vai usar privateKey.pem para assinar
    }
}
