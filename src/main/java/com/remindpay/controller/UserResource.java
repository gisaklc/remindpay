package com.remindpay.controller;

import com.remindpay.dto.AccessToken;
import com.remindpay.dto.UserLoginDto;
import com.remindpay.dto.UserRequestDto;
import com.remindpay.mapper.UserMapper;
import com.remindpay.model.User;
import com.remindpay.service.UserService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {

    @Inject
    UserService userService;

    @Inject
    UserMapper userMapper;

    @POST
    @Transactional
    public Response createAccount(@Valid UserRequestDto userRequestDto) {

         var user = userMapper.toEntity(userRequestDto);
         userService.create(user);

        return Response.status(Response.Status.CREATED).build();
    }

    @POST
    @Path("/login")
    public Response login(UserLoginDto dto) {
        AccessToken accessToken = userService.authenticate(dto.getEmail(), dto.getPassword());

        return Response.ok(accessToken).build();

    }

}
