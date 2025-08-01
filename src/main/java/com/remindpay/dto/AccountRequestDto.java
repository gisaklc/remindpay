package com.remindpay.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

public class AccountRequestDto {

    @NotBlank(message = "Informe o nome da conta.")
    private String name;

    @NotNull(message = "Informe o id do usuario da conta.")
    private UUID userId;

    @NotNull(message = "Informe o id da categoria.")
    private UUID categoryId;

    @NotNull(message = "Informe o dia de Vencimento da conta")
    @Min(1)
    @Max(31)
    private Integer dueDay;

    @NotNull(message = "Informe o valor da conta.")
    private BigDecimal value;

    @NotNull(message = "Informe o status da conta.")
    private String status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(UUID categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getDueDay() {
        return dueDay;
    }

    public void setDueDay(Integer dueDay) {
        this.dueDay = dueDay;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }
}
