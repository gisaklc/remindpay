package com.remindpay.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

public class AccountResponseDto {


    private UUID id;
    private String name;
    private CategoryResponseDto category;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CategoryResponseDto getCategory() {
        return category;
    }

    public void setCategory(CategoryResponseDto category) {
        this.category = category;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public static class UserAccountRequestDto {


        @NotNull(message = "Informe o ID do usuário.")
        private UUID userId;

        @NotNull(message = "Informe o ID da conta.")
        private UUID accountId;

        @NotNull(message = "Informe o dia de vencimento.")
        @Min(value = 1)
        @Max(value = 31)
        private Integer dueDay;

        @NotNull(message = "Informe o Email")
        private String emailNotify;

        @NotNull(message = "Informe o valor base.")
        private BigDecimal baseValue;

        public UserAccountRequestDto() {
        }

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

        public String getEmailNotify() {
            return emailNotify;
        }

        public void setEmailNotify(String emailNotify) {
            this.emailNotify = emailNotify;
        }

        public Integer getDueDay() {
            return dueDay;
        }

        public void setDueDay(Integer dueDay) {
            this.dueDay = dueDay;
        }

        public BigDecimal getBaseValue() {
            return baseValue;
        }

        public void setBaseValue(BigDecimal baseValue) {
            this.baseValue = baseValue;
        }
    }
}
