package com.remindpay.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.util.UUID;

public class UserAccountRequestDTO {

    @NotNull(message = "Informe o id do usuario")
    private UUID userId;

    @NotNull(message = "Informe o id da conta")
    private UUID accountId;

    @NotNull(message = "Informe o dia de Vencimento da conta")
    @Min(1)
    @Max(31)
    private Integer dueDay;

    @NotNull(message = "Informe o Email para notificação")
    @Email(message = "Email inválido")
    private String emailNotify;

    @NotNull
    @DecimalMin(value = "0.01", message = "O valor da conta deve ser maior do que zero")
    private BigDecimal baseValue;

    public UserAccountRequestDTO() {}

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public UUID getAccountId() {
        return accountId;
    }

    public void setAccountId(UUID accountId) {
        this.accountId = accountId;
    }

    public Integer getDueDay() {
        return dueDay;
    }

    public void setDueDay(Integer dueDay) {
        this.dueDay = dueDay;
    }

    public String getEmailNotify() {
        return emailNotify;
    }

    public void setEmailNotify(String emailNotify) {
        this.emailNotify = emailNotify;
    }

    public BigDecimal getBaseValue() {
        return baseValue;
    }

    public void setBaseValue(BigDecimal baseValue) {
        this.baseValue = baseValue;
    }
}
