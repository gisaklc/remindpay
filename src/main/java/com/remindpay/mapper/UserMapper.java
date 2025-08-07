package com.remindpay.mapper;

import com.remindpay.dto.UserRequestDto;
import com.remindpay.dto.UserResponseDto;
import com.remindpay.model.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "cdi")
public interface UserMapper {

    User toEntity(UserRequestDto dto);

    List<UserResponseDto> toDto(List<User> users);
}
