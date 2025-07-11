package com.remindpay.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public class AccountRequestDto {

    @NotBlank(message = "Informe o nome da conta.")
    private String name;

    @NotNull(message = "Informe o id da categoria.")
    private UUID categoryId;

    public UUID getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(UUID categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
