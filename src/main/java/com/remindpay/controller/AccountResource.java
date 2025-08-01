package com.remindpay.controller;

import com.remindpay.dto.AccountRequestDto;
import com.remindpay.dto.StatusConta;
import com.remindpay.mapper.AccountMapper;
import com.remindpay.model.Account;
import com.remindpay.model.Category;
import com.remindpay.model.User;
import com.remindpay.service.AccountService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;


import java.util.List;

@SecurityRequirement(name = "jwt")
@Path("/api/accounts")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AccountResource {

    @Inject
    AccountService accountService;
    @Inject
    AccountMapper accountMapper;

    @POST
    @RolesAllowed({"USER", "ADMIN"})
    public Response createAccount(@Valid AccountRequestDto accountRequestDto) {

        Account account = new Account();
        account.setName(accountRequestDto.getName());
        account.setCategory(new Category());
        account.getCategory().setId(accountRequestDto.getCategoryId());
        account.setValue(accountRequestDto.getValue());
        account.setStatus(StatusConta.valueOf(accountRequestDto.getStatus().toUpperCase()));
        account.setDueDay(accountRequestDto.getDueDay());

        User user = new User();
        user.setId(accountRequestDto.getUserId());
        account.setUser(user);

        accountService.create(account);

        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    public Response listCount() {
        List<Account> accounts = accountService.listAll();
        return Response.ok(accountMapper.toDtoList(accounts)).build();
    }

}
