package com.remindpay.dto;

import java.math.BigDecimal;
import java.util.UUID;

public class UserAccountResponseDto {

    private UUID id;
    private Integer dueDay;
    private String emailNotify;
    private BigDecimal baseValue;

    private UUID userId;
    private String userName;

    private UUID accountId;
    private String accountName;

}
