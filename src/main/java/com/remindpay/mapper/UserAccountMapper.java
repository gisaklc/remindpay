package com.remindpay.mapper;

import com.remindpay.dto.UserAccountRequestDTO;
import com.remindpay.exceptions.GenericExceptionMapper;
import com.remindpay.model.Account;
import com.remindpay.model.User;
import com.remindpay.model.UserAccount;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.UUID;
import java.util.logging.Logger;

@Mapper(componentModel = "cdi")
public interface UserAccountMapper {


    @Mapping(target = "user", source = "userId", qualifiedByName = "fromUserId")
    @Mapping(target = "account", source = "accountId", qualifiedByName = "fromAccountId")
    UserAccount toEntity(UserAccountRequestDTO dto);

    @Named("fromUserId")
    default User fromUserId(UUID id) {
        if (id == null) return null;
        return new User(id);
    }
    @Named("fromAccountId")
    default Account fromAccountId(UUID id) {
        if (id == null) return null;
        return  new Account(id);
    }
}
