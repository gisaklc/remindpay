package com.remindpay.mapper;

import com.remindpay.dto.AccountRequestDto;
import com.remindpay.dto.AccountResponseDto;
import com.remindpay.model.Account;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "cdi")
public interface AccountMapper {

    Account toEntity(AccountRequestDto dto);

    List<AccountResponseDto> toDtoList(List<Account> accounts);
}
