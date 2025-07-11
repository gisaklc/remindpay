package com.remindpay.dto;

import jakarta.validation.constraints.NotBlank;


public class CategoryRequestDto {
    @NotBlank(message = "O nome da categoria é obrigatório.")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
