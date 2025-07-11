package com.remindpay.mapper;

import com.remindpay.dto.UserRequestDto;
import com.remindpay.dto.UserResponseDto;
import com.remindpay.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi")
public interface UserMapper {

    User toEntity(UserRequestDto dto);

    UserResponseDto toDto(User entity);
}
