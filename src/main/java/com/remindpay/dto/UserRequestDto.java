package com.remindpay.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.util.List;

public class UserRequestDto {

    @NotBlank(message = "Informe o nome")
    private String name;

    @NotBlank(message = "Informe o email")
    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$",message = "Formato de email inválido" )
    private String email;

    @NotBlank(message = "Informe a senha")
    private String password;

    @Pattern(
            regexp = "^\\d{10,11}$", // Ex: 21972265407
            message = "Informe apenas DDD + número. Ex: 21972265407"
    )
    private String phoneNumber;


    private List<String> roles; // opcional, se quiser permitir criação com papéis

    private UserRequestDto() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
